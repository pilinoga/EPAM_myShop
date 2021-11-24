package com.epam.shop.service.impl;

import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.exception.ConnectionException;
import com.epam.shop.service.api.ProductService;
import com.epam.shop.service.dto.ProductDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductServiceImplTest {
    private static ProductDto product;
    private static ProductService service;
    private static final long ID = 34;
    private static final String NAME = "iphone";
    private static final String DESCRIPTION = "7 32 gb black";
    private static final String SPECIFICATION = "specification";
    private static final double PRICE = 125.5;

    @BeforeAll
    static void setUp() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
        service = new ProductServiceImpl();
        product = new ProductDto(ID,NAME,DESCRIPTION,PRICE,SPECIFICATION);
    }

    @AfterAll
    static void shutDownPool() throws ConnectionException {
        ConnectionPool.getInstance().shutDown();
    }

    @Test
    void shouldValidateProductPositive(){
        boolean actual = service.isValid(product);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldValidateProductNegative(){
        product.setName("");
        product.setPrice(123.123435);
        boolean actual = service.isValid(product);
        Assertions.assertFalse(actual);
    }

    @Test
    void shouldReturnTopSalesProductList(){
        int expectedProduct1Id = 11;
        int expectedProduct2Id = 26;
        List<ProductDto> topSales = service.getTopSales();
        int actualProduct1Id = (int) topSales.get(0).getId();
        int actualProduct2Id = (int) topSales.get(1).getId();
        Assertions.assertEquals(expectedProduct1Id,actualProduct1Id);
        Assertions.assertEquals(expectedProduct2Id,actualProduct2Id);
    }

}
