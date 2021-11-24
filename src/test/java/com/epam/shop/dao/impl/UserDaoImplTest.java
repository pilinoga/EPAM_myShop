package com.epam.shop.dao.impl;

import com.epam.shop.dao.Role;
import com.epam.shop.dao.api.UserDao;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.entity.User;
import com.epam.shop.dao.exception.ConnectionException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserDaoImplTest {
    private static UserDao dao;
    private static User user;
    private static final int ROLE_ID = 2;
    private static final int ID = 87;
    private static final String LOGIN = "testLogin";
    private static final String PASSWORD = "testPassword1234";

    @BeforeAll
    static void setUp() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
        dao = new UserDaoImpl();
        user = new User(ID,ROLE_ID,LOGIN,PASSWORD);
    }

    @AfterAll
    static void shutDownPool() throws ConnectionException {
        ConnectionPool.getInstance().shutDown();
    }

    @Test
    void shouldSaveAndReturnUser(){
        User savedUser = dao.save(user);
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(user,savedUser);
    }

    @Test
    void shouldReturnUserFromDBById(){
        User actualUser = dao.findById(ID);
        Assertions.assertEquals(user,actualUser);
    }

    @Test
    void shouldUpdateFieldsInDBForUser(){
        String newPassword = "newPassword123";
        User expected = new User(ID,ROLE_ID,LOGIN,newPassword);
        User actual = dao.update(expected);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void shouldDeleteUserFromDB(){
        boolean actual = dao.delete(user);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnListOfUsers(){
        int expectedSize = 0;
        int actualSize = dao.findAll().size();
        Assertions.assertNotEquals(expectedSize,actualSize);
    }

    @Test
    void shouldCheckExistenceOfUser_WithLoginAndPassword(){
        boolean actual = dao.findByLoginAndPassword(user);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnUserRole(){
        Role expected = Role.CUSTOMER;
        Role actual = dao.getRoleByLogin(user);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void shouldCheckUniquenessOfLogin_BeforeSavingNewUser(){
        boolean actual = dao.isUniqueLogin(user);
        Assertions.assertFalse(actual);
    }

    @Test
    void shouldReturnUserByLogin(){
        User expected = user;
        User actual = dao.findByLogin(LOGIN);
        Assertions.assertEquals(expected,actual);
    }
}
