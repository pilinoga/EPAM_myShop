package com.epam.shop.dao.connection;

import com.epam.shop.dao.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final String PROPERTIES_FILE = "database.properties";
    private static final String POOL_SIZE = "pool-size";
    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> usedConnections;

    private ConnectionPool(){}

    public static synchronized ConnectionPool getInstance()  {
        return Singleton.INSTANCE;
    }

    /**
     * Method to initialize connection pool
     *
     * @throws ConnectionException
     */
    public void initialize() throws ConnectionException{
        try {
            final int poolSize = readPoolSize();
            availableConnections = new ArrayBlockingQueue<>(poolSize);
            usedConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                final Connection connection = ConnectionFactory.createConnection();
                final ProxyConnection proxyConnection = new ProxyConnection(connection);
                availableConnections.add(proxyConnection);
            }
        } catch (IOException e) {
            logger.error("Connection pool wasn't initialized");
            throw new ConnectionException(e);
        }
    }

    /**
     * Method to take a connection
     *
     * @return connection from connection pool
     * @throws ConnectionException
     */
    public Connection takeConnection() throws ConnectionException {
        final ProxyConnection connection;
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Failed to take a connection");
            Thread.currentThread().interrupt();
            throw new ConnectionException(e);
        }
        return connection;
    }

    /**
     * Method to release a connection to connection pool
     *
     * @throws ConnectionException
     */
    public void releaseConnection(Connection connection) throws ConnectionException {
        if (connection != null) {
            usedConnections.remove(connection);
            try {
                availableConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.error("Failed to release a connection");
                Thread.currentThread().interrupt();
                throw new ConnectionException(e);
            }
        }
    }

    /**
     * Method to shut down connection pool
     *
     * @throws ConnectionException
     */
    public void shutDown() throws ConnectionException {
        try {
            shutDown(availableConnections);
            shutDown(usedConnections);
        } catch (SQLException e) {
            logger.error("Connection pool wasn't shut down");
            throw new ConnectionException(e.getMessage(),e);
        }
    }

    /**
     * Method to shut down connections queue
     *
     * @throws SQLException
     */
    private void shutDown(Queue<ProxyConnection> connections) throws SQLException {
        for (ProxyConnection connection : connections) {
            connection.realClose();
        }
    }

    /**
     * Method to read pool size from property file
     *
     * @throws IOException
     */
    private int readPoolSize() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(PROPERTIES_FILE);
        Properties properties = new Properties();
        properties.load(is);
        return Integer.parseInt(properties.getProperty(POOL_SIZE));

    }

    private static class Singleton{
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
