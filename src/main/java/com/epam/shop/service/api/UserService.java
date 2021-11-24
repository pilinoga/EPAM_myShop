package com.epam.shop.service.api;

import com.epam.shop.dao.Role;
import com.epam.shop.service.dto.UserDto;

public interface UserService extends Service<UserDto,Integer>{

    /**
     * Method to check for user existence
     *
     * @param user object for checking
     * @return result of checking
     */
    boolean isExist(UserDto user);

    /**
     * Method to receive role of user
     *
     * @param user contains login for getting role from DB
     * @return role of user
     */
    Role getRoleByLogin(UserDto user);

    /**
     * Method to check for login uniqueness
     *
     * @param user contains login for checking uniqueness
     * @return result of checking
     */
    boolean isUniqueLogin(UserDto user);

    /**
     * Method for user validation
     *
     * @param user contains login and password for validation
     * @return result of validation
     */
    boolean isValid(UserDto user);

    /**
     * Method to receive user by login
     *
     * @param login login of user
     * @return userDto from DB
     */
    UserDto getByLogin(String login);
}
