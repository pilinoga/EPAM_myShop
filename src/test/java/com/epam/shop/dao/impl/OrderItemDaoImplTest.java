package com.epam.shop.dao.impl;

import com.epam.shop.dao.api.OrderItemDao;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.entity.OrderItem;
import com.epam.shop.dao.exception.ConnectionException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class OrderItemDaoImplTest {
    private static OrderItemDao dao;
    private static OrderItem item;
    private static final long ID = 469;
    private static final long ORDER_ID = 169;
    private static final long PRODUCT_ID = 16;
    private static final int QUANTITY = 1;

    @BeforeAll
    static void setUp() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
        dao = new OrderItemDaoImpl();
        item = new OrderItem(ID,ORDER_ID,PRODUCT_ID,QUANTITY);
    }

    @AfterAll
    static void shutDownPool() throws ConnectionException {
        ConnectionPool.getInstance().shutDown();
    }

    @Test
    void shouldSaveAndReturnOrderItem() {
        OrderItem saved = dao.save(item);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(item, saved);
    }

    @Test
    void shouldReturnOrderItemFromDBById() {
        OrderItem orderFromDB = dao.findById(ID);
        Assertions.assertNotNull(orderFromDB);
    }

    @Test
    void shouldUpdateFieldsInDBForOrderItem() {
        OrderItem expected = new OrderItem(ID,ORDER_ID,PRODUCT_ID,2);
        OrderItem actual = dao.update(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteOrderItemFromDB(){
        boolean actual = dao.delete(item);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnListOfOrderItems() {
        int expectedSize = 0;
        int actualSize = dao.findAll().size();
        Assertions.assertNotEquals(expectedSize, actualSize);
    }

    @Test
    void shouldReturnListOfItemsForOrder() {
        List<OrderItem> orderItems = dao.findFromOrder(ORDER_ID);
        Assertions.assertNotNull(orderItems);
    }
}
