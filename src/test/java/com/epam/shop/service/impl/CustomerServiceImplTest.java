package com.epam.shop.service.impl;

import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.exception.ConnectionException;
import com.epam.shop.service.api.CustomerService;
import com.epam.shop.service.dto.CustomerDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CustomerServiceImplTest {
    private static CustomerDto customer;
    private static CustomerService service;
    private static final long ID = 87;
    private static final String FIRST_NAME= "name";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL_PARAMETER = "email@email.com";
    private static final long PHONE_NUMBER = 331112233;
    private static final double CARD_BALANCE = 500.80;
    private static final boolean STATUS = false;
    private static final int ROWS_ON_PAGE = 10;

    @BeforeAll
    static void setUp() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
        service = new CustomerServiceImpl();
        customer = new CustomerDto(FIRST_NAME,LAST_NAME,EMAIL_PARAMETER,PHONE_NUMBER,CARD_BALANCE,STATUS);
    }

    @AfterAll
    static void shutDownPool() throws ConnectionException {
        ConnectionPool.getInstance().shutDown();
    }

    @Test
    void shouldCheckExistenceOfCustomerInTable(){
        boolean actual = service.isInfoExist(ID);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnListOfCustomersForPageOnWebsite(){
        int actual = service.getRowsForPage(1).size();
        Assertions.assertTrue(actual <= ROWS_ON_PAGE);
    }

    @Test
    void shouldReturnCustomerFromDBById(){
        CustomerDto actual = service.getById(ID);
        Assertions.assertEquals(customer,actual);
    }
    @Test
    void shouldUpdateFieldsInDBForCustomer(){
        CustomerDto actual = service.update(customer);
        Assertions.assertEquals(customer,actual);
    }

    @Test
    void shouldValidateCustomer(){
        boolean actual = service.isValid(customer);
        Assertions.assertTrue(actual);
    }
}
