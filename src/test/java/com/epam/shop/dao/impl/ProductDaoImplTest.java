package com.epam.shop.dao.impl;

import com.epam.shop.dao.api.ProductDao;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.entity.Product;
import com.epam.shop.dao.exception.ConnectionException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProductDaoImplTest {
    private static ProductDao dao;
    private static Product product;
    private static final long ID = 34;
    private static final String NAME = "iphone";
    private static final String DESCRIPTION = "7 32 gb black";
    private static final String SPECIFICATION = "specification";
    private static final double PRICE = 125.5;
    private static final int ROWS_ON_PAGE = 10;

    @BeforeAll
    static void setUp() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
        dao = new ProductDaoImpl();
        product = new Product(ID,NAME,DESCRIPTION,PRICE,SPECIFICATION);
    }

    @AfterAll
    static void shutDownPool() throws ConnectionException {
        ConnectionPool.getInstance().shutDown();
    }

    @Test
    void shouldSaveAndReturnProduct(){
        Product saved = dao.save(product);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(product,saved);
    }

    @Test
    void shouldReturnProductFromDBById(){
        Product actual = dao.findById(ID);
        Assertions.assertEquals(product,actual);
    }

    @Test
    void shouldUpdateFieldPriceInDBForProduct(){
        Product expected = new Product(ID,NAME,DESCRIPTION,150.50,SPECIFICATION);
        Product actual = dao.update(expected);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void shouldDeleteProductFromDB(){
        boolean actual = dao.delete(product);
        Assertions.assertTrue(actual);
    }
    @Test
    void shouldReturnListOfProducts(){
        int expectedSize = 0;
        int actualSize = dao.findAll().size();
        Assertions.assertNotEquals(expectedSize,actualSize);
    }

    @Test
    void shouldReturnListOfProductsForPageOnWebsite(){
        int actual = dao.findRowsForPage(1).size();
        Assertions.assertTrue(actual <= ROWS_ON_PAGE);
    }
}
