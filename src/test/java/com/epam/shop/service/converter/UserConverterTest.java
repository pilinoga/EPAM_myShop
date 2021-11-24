package com.epam.shop.service.converter;

import com.epam.shop.dao.Role;
import com.epam.shop.dao.entity.User;
import com.epam.shop.service.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserConverterTest {
    private static User user;
    private static UserDto userDto;
    private static Converter<UserDto, User> converter;
    private static final int ROLE_ID = 2;
    private static final Role ROLE = Role.CUSTOMER;
    private static final String LOGIN = "kirillLogin";
    private static final String PASSWORD= "password12345";

    @BeforeAll
    static void setUp()  {
        converter =new UserConverter();
        user = new User(ROLE_ID,LOGIN,PASSWORD);
        userDto = new UserDto(LOGIN,PASSWORD,Role.CUSTOMER);
    }

    @Test
    void shouldCovertFromUserToUserDto(){
        UserDto actual = converter.convert(user);
        Assertions.assertEquals(userDto,actual);
    }

    @Test
    void shouldCovertFromUserDtoToUser(){
        User actual = converter.convert(userDto);
        Assertions.assertEquals(user,actual);
    }

    @Test
    void shouldCovertRole(){
        int expected = user.getRoleId();
        int actual = userDto.getRole().getIdRole();
        Assertions.assertEquals(expected,actual);
    }
}
