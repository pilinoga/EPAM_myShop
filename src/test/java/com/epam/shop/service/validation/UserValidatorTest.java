package com.epam.shop.service.validation;

import com.epam.shop.service.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserValidatorTest {
    private static Validator<UserDto> validator;
    private static UserDto user;
    private static final String LOGIN_TEST= "kirillLog";
    private static final String PASSWORD_TEST = "password12345";

    @BeforeAll
    static void setUp(){
        validator = new UserValidator();
        user = new UserDto(LOGIN_TEST,PASSWORD_TEST);
    }

    @Test
    void shouldValidatePasswordAndLoginPositive(){
        boolean result = validator.isValid(user);
        Assertions.assertTrue(result);
    }
    @Test
    void shouldValidatePasswordAndLoginNegative(){
        user.setPassword("p");
        user.setLogin("l");
        boolean result = validator.isValid(user);
        Assertions.assertFalse(result);
    }
}
