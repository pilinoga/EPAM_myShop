package com.epam.shop.service.validation;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.service.dto.UserDto;

/**
 * The password's first character must be a letter, it must contain at least 8 characters
 * and no more than 20 characters and no characters other than letters, numbers
 * and the underscore may be used.
 *
 * The login's first character must be a letter, it must contain at least 5 characters
 * and no more than 15 characters and no characters other than letters, numbers
 * and the underscore may be used.
 */

public class UserValidator implements Validator<UserDto>{
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String LOGIN_REGEX = "^[a-zA-Z]\\w{5,15}$";
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9]{8,20}$";

    /**
     * Method for context validation
     *
     * @param context contains parameters for validation
     * @return result of validation
     */
    @Override
    public boolean isValid(RequestContext context) {
        String login = context.getParameter(LOGIN_PARAMETER);
        String password = context.getParameter(PASSWORD_PARAMETER);
        return login.matches(LOGIN_REGEX) &&
                password.matches(PASSWORD_REGEX);
    }
    /**
     * Method for user validation
     *
     * @param user contains login and password for validation
     * @return result of validation
     */
    @Override
    public boolean isValid(UserDto user){
        String login = user.getLogin();
        String password = user.getPassword();
        return login.matches(LOGIN_REGEX) &&
                password.matches(PASSWORD_REGEX);
    }

}

