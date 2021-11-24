package com.epam.shop;

import com.epam.shop.dao.exception.ConnectionException;
import com.epam.shop.service.dto.UserDto;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {
    private static final String LOGIN_TEST= "testLogin";
    private static final String PASSWORD_TEST = "testPassword1234";

    public static void main(String[] args) throws ConnectionException {
//        ConnectionPool.getInstance().initialize();
        UserDto user = new UserDto(LOGIN_TEST,PASSWORD_TEST);

        String s = passwordToMd5(user);
        System.out.println(s);


//        ConnectionPool.getInstance().shutDown();
    }
    public static String passwordToMd5(UserDto user) {
        String password = user.getPassword();
        byte[] secretByte = new byte[0];
        try {
            secretByte = MessageDigest.getInstance("md5")
                    .digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
        }
        StringBuilder md5Code = new StringBuilder(new BigInteger(1, secretByte).toString(16));
        for (int i = 0; i < 32 - md5Code.length(); i++) {
            md5Code.insert(0, 0);
        }
        return md5Code.toString();
    }
}


