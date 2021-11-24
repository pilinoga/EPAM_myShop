package com.epam.shop.dao.connection;

import com.epam.shop.dao.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);
    private static final String PROPERTIES_FILE = "database.properties";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String DRIVER = "driver";

    /**
     * Method to create a connection to database
     *
     * @return connection to database
     * @throws ConnectionException
     */
    public static Connection createConnection() throws ConnectionException {
        Connection connection;
        try {
            Properties properties = new Properties();
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(PROPERTIES_FILE);
            properties.load(is);
            String connectionURL = properties.getProperty(URL);
            String userName = properties.getProperty(USER);
            String password = properties.getProperty(PASSWORD);
            Class.forName(properties.getProperty(DRIVER));
            connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            logger.error("Connection wasn't created");
            throw new ConnectionException(e.getMessage(),e);
        }
        return connection;
    }

}




