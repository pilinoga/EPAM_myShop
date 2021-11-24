package com.epam.shop.dao.impl;

import com.epam.shop.dao.api.OrderDao;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.entity.Order;
import com.epam.shop.dao.exception.ConnectionException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class OrderDaoImplTest {
    private static OrderDao dao;
    private static Order order;
    private static final long ID = 169;
    private static final long CUSTOMER_ID = 87;
    private static final Date DATE = Date.valueOf(LocalDate.now());
    private static final double PRICE = 250.50;
    private static final boolean STATUS = true;
    private static final int ROWS_ON_PAGE = 10;

    @BeforeAll
    static void setUp() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
        dao = new OrderDaoImpl();
        order = new Order(ID, CUSTOMER_ID, DATE, PRICE, STATUS);
    }

    @AfterAll
    static void shutDownPool() throws ConnectionException {
        ConnectionPool.getInstance().shutDown();
    }

    @Test
    void shouldSaveAndReturnOrder() {
        Order saved = dao.save(order);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(order, saved);
    }

    @Test
    void shouldReturnOrderFromDBById() {
        Order orderFromDB = dao.findById(ID);
        Assertions.assertNotNull(orderFromDB);
    }

    @Test
    void shouldUpdateFieldsInDBForOrder() {
        Order expected = new Order(ID, CUSTOMER_ID, DATE, 180.50, STATUS);
        Order actual = dao.update(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteOrderFromDB() {
        boolean actual = dao.delete(order);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnListOfOrders() {
        int expectedSize = 0;
        int actualSize = dao.findAll().size();
        Assertions.assertNotEquals(expectedSize, actualSize);
    }

    @Test
    void shouldReturnListOfOrdersForCustomer() {
        List<Order> orders = dao.findForCustomer(CUSTOMER_ID, 1);
        Assertions.assertNotNull(orders);
    }

    @Test
    void shouldReturnListOfOrdersForPageOnWebsite(){
        int actual = dao.findRowsForPage(1).size();
        Assertions.assertTrue(actual <= ROWS_ON_PAGE);
    }
}