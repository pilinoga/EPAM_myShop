package com.epam.shop.service.impl;

import com.epam.shop.dao.DaoFactory;
import com.epam.shop.dao.Role;
import com.epam.shop.dao.api.UserDao;
import com.epam.shop.dao.entity.User;
import com.epam.shop.service.api.UserService;
import com.epam.shop.service.converter.Converter;
import com.epam.shop.service.converter.UserConverter;
import com.epam.shop.service.dto.UserDto;
import com.epam.shop.service.validation.UserValidator;
import com.epam.shop.service.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final Converter <UserDto, User> converter;
    private final UserDao userDao;
    private final Validator<UserDto> validator;
    private static final String ALGORITHM = "md5";

    public UserServiceImpl() {
        converter = new UserConverter();
        userDao = DaoFactory.getInstance().getUserDao();
        validator = new UserValidator();
    }

    @Override
    public boolean isValid(UserDto userDto){
        return validator.isValid(userDto);
    }

    @Override
    public UserDto create(UserDto userDto) {
        final String encryptedPassword = passwordToMd5(userDto);
        userDto.setPassword(encryptedPassword);
        User user = userDao.save(converter.convert(userDto));
        return converter.convert(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userDao.update(converter.convert(userDto));
        return converter.convert(user);
    }

    @Override
    public boolean delete(UserDto userDto) {
        return userDao.delete(converter.convert(userDto));
    }

    @Override
    public UserDto getById(Integer id) {
        return converter.convert(userDao.findById(id));
    }

    @Override
    public List<UserDto> getAll() {
       return userDao.findAll().stream()
               .map(converter::convert).collect(Collectors.toList());
    }

    @Override
    public boolean isExist(UserDto userDto) {
        if (validator.isValid(userDto)) {
            final String encryptedPassword = passwordToMd5(userDto);
            userDto.setPassword(encryptedPassword);
            User user = converter.convert(userDto);
            return userDao.findByLoginAndPassword(user);
        }else {
           return false;
        }
    }

    @Override
    public Role getRoleByLogin(UserDto user){
        return userDao.getRoleByLogin(converter.convert(user));
    }

    @Override
    public boolean isUniqueLogin(UserDto user) {
            return userDao.isUniqueLogin(converter.convert(user));
    }

    @Override
    public UserDto getByLogin(String login) {
        User user = userDao.findByLogin(login);
        return converter.convert(user);
    }

    /**
     * Method to encrypt user password by md5
     *
     * @param user contains password for encrypting
     * @return encrypted password
     */
    private String passwordToMd5(UserDto user) {
        String password = user.getPassword();
        byte[] secretByte = new byte[0];
        try {
            secretByte = MessageDigest.getInstance(ALGORITHM)
                    .digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            logger.error("Algorithm wasn't found");
        }
        StringBuilder md5Code = new StringBuilder(new BigInteger(1, secretByte).toString(16));
        for (int i = 0; i < 32 - md5Code.length(); i++) {
            md5Code.insert(0, 0);
        }
        return md5Code.toString();
    }

    /**
     * Method to decrypt user password
     *
     * @param user contains password for decrypting
     * @return decrypted password
     */
    private String passwordFromMd5(User user) {
        String password = user.getPassword();
        byte[] secretByte = new byte[0];
        try {
            secretByte = MessageDigest.getInstance(ALGORITHM)
                    .digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            logger.error("Algorithm wasn't found");
        }
        StringBuilder md5Code = new StringBuilder(new BigInteger(1, secretByte).toString(16));
        for (int i = 0; i < 32 - md5Code.length(); i++) {
            md5Code.insert(0, 0);
        }
        return md5Code.toString();
    }
}
