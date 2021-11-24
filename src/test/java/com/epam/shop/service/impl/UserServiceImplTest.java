package com.epam.shop.service.impl;

import com.epam.shop.dao.Role;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.exception.ConnectionException;
import com.epam.shop.service.api.UserService;
import com.epam.shop.service.dto.UserDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserServiceImplTest {
    private static UserDto user;
    private static UserService service;
    private static final String LOGIN_TEST= "testLogin";
    private static final String PASSWORD_TEST = "5cb8911aa368f1a1d063f4241696e581";

    @BeforeAll
    static void setUp() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
        service = new UserServiceImpl();
        user = new UserDto(LOGIN_TEST,PASSWORD_TEST);
    }

    @AfterAll
    static void shutDownPool() throws ConnectionException {
        ConnectionPool.getInstance().shutDown();
    }

    @Test
    void shouldReturnRoleByPassword(){
        Role expected = Role.CUSTOMER;
        Role actual = service.getRoleByLogin(user);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void shouldCheckUserExistence(){
        boolean actual = service.isExist(user);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldCheckUniquenessOfPassword(){
        boolean actual = service.isUniqueLogin(user);
        Assertions.assertFalse(actual);
    }

    @Test
    void shouldReturnUserByLogin(){
        UserDto expected = user;
        user.setId(87);
        user.setRole(Role.CUSTOMER);
        UserDto actual = service.getByLogin(LOGIN_TEST);
        Assertions.assertEquals(expected,actual);
    }
}
