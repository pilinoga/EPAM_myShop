package com.epam.shop.dao.connection;

import com.epam.shop.dao.exception.ConnectionException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionPoolTest {

    @BeforeAll
    static void initializePool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    @AfterAll
    static void shutDownPool() throws ConnectionException {
        ConnectionPool.getInstance().shutDown();
    }

    @Test
    void shouldBeTheSameInstance(){
        ConnectionPool pool1= ConnectionPool.getInstance();
        ConnectionPool pool2= ConnectionPool.getInstance();
        Assertions.assertEquals(pool1,pool2);
    }

    @Test
    void shouldReturnOneConnectionToDB() throws ConnectionException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        Assertions.assertNotNull(connection);
    }

}
