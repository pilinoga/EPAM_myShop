package com.epam.shop.service.impl;

import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.exception.ConnectionException;
import com.epam.shop.service.api.OrderItemService;
import com.epam.shop.service.dto.OrderItemDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class OrderItemServiceImplTest {
    private static OrderItemDto item;
    private static OrderItemService service;
    private static final long ID = 469;
    private static final long ORDER_ID = 169;
    private static final long PRODUCT_ID = 16;
    private static final int QUANTITY = 1;


    @BeforeAll
    static void setUp() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
        service = new OrderItemServiceImpl();
        item = new OrderItemDto(ID,ORDER_ID,PRODUCT_ID,QUANTITY);
    }

    @AfterAll
    static void shutDownPool() throws ConnectionException {
        ConnectionPool.getInstance().shutDown();
    }

    @Test
    void shouldReturnItemsFromOrder(){
        List<OrderItemDto> orderItems = service.findFromOrder(ORDER_ID);
        int expected = 2;
        int actual = orderItems.size();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void shouldReturnListOfQuantityForEachProduct(){
        int expected = 15;
        List<OrderItemDto> items = service.getQuantity();
        int actual = items.get(0).getQuantity();
        Assertions.assertEquals(expected,actual);
    }

}
