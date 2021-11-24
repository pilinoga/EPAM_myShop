package com.epam.shop.dao.impl;

import com.epam.shop.dao.api.OrderDao;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.entity.Order;
import com.epam.shop.dao.exception.ConnectionException;
import com.epam.shop.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);
    private ConnectionPool pool = ConnectionPool.getInstance();
    private static final String TABLE = "orders";
    private static final String ID_COLUMN = "id";
    private static final String CUSTOMER_ID_COLUMN = "customer_id";
    private static final String DATE_COLUMN = "date";
    private static final String PRICE_COLUMN = "price";
    private static final String STATUS_COLUMN = "status";
    private static final int ROWS_ON_PAGE = 10;

    @Override
    public Order save(Order order) {
        ResultSet resultSet = null;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL.SAVE.getQuery(), new String[] {ID_COLUMN})) {
            statement.setLong(1, order.getCustomerId());
            statement.setDate(2, order.getOrderDate());
            statement.setDouble(3, order.getPrice());
            statement.setBoolean(4, order.getStatus());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("Order wasn't saved");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return order;
    }

    @Override
    public Order findById(Long key) {
        Order order = null;
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_BY_ID.getQuery())){
            statement.setString(1, String.valueOf(key));
            resultSet  = statement.executeQuery();
            if(resultSet.next()){
                order = new Order();
                order.setId(resultSet.getLong(ID_COLUMN));
                order.setCustomerId(resultSet.getLong(CUSTOMER_ID_COLUMN));
                order.setOrderDate(resultSet.getDate(DATE_COLUMN));
                order.setPrice(resultSet.getDouble(PRICE_COLUMN));
                order.setStatus(resultSet.getBoolean(STATUS_COLUMN));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("Order wasn't found");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.UPDATE.getQuery())) {
            statement.setBoolean(1, order.getStatus());
            statement.setDouble(2, order.getPrice());
            statement.setLong(3, order.getId());
            statement.executeUpdate() ;
        } catch (SQLException | ConnectionException e) {
            logger.error("Order wasn't updated");
            throw new DaoException(e.getMessage(), e);
        }
        return order;
    }

    @Override
    public boolean delete(Order order) {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.DELETE.getQuery())) {
            statement.setLong(1, order.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionException e) {
            logger.error("Order wasn't deleted");
            throw new DaoException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.FIND_ALL.getQuery());
             ResultSet resultSet =statement.executeQuery()){
            while(resultSet.next()){
                long id  = resultSet.getLong(ID_COLUMN);
                long customerId  = resultSet.getLong(CUSTOMER_ID_COLUMN);
                Date orderDate = resultSet.getDate(DATE_COLUMN);
                double price = resultSet.getDouble(PRICE_COLUMN);
                boolean status =  resultSet.getBoolean(STATUS_COLUMN);
                list.add(new Order(id,customerId,orderDate,price,status));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("List was not received");
            throw new DaoException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Order> findForCustomer(Long key, int pageNumber) {
        List<Order> list = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.FIND_BY_CUSTOMER.getQuery())){
            statement.setLong(1, key);
            statement.setInt(2, (pageNumber-1)*ROWS_ON_PAGE);
            ResultSet resultSet =statement.executeQuery();
            while(resultSet.next()){
                long id  = resultSet.getLong(ID_COLUMN);
                long customerId  = resultSet.getLong(CUSTOMER_ID_COLUMN);
                Date orderDate = resultSet.getDate(DATE_COLUMN);
                double price = resultSet.getDouble(PRICE_COLUMN);
                boolean status =  resultSet.getBoolean(STATUS_COLUMN);
                list.add(new Order(id,customerId,orderDate,price,status));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("List was not received");
            throw new DaoException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Order> findRowsForPage(int pageNumber) {
        List<Order> list = new ArrayList<>();
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(OrderDaoImpl.SQL.FIND_10_ROWS.getQuery())){
            statement.setInt(1, (pageNumber-1)*ROWS_ON_PAGE);
            resultSet  = statement.executeQuery();
            while(resultSet.next()){
                long id  = resultSet.getLong(ID_COLUMN);
                long customerId  = resultSet.getLong(CUSTOMER_ID_COLUMN);
                Date orderDate = resultSet.getDate(DATE_COLUMN);
                double price = resultSet.getDouble(PRICE_COLUMN);
                boolean status =  resultSet.getBoolean(STATUS_COLUMN);
                list.add(new Order(id,customerId,orderDate,price,status));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("List was not received");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return list;
    }

    private enum SQL {
        SAVE("INSERT INTO orders (customer_id, date, price, status ) VALUES (?, ?, ?, ?) "),
        FIND_BY_ID("SELECT id, customer_id, date, price, status FROM orders WHERE id = ? "),
        UPDATE("UPDATE orders SET status = ?, price = ?  WHERE id = ? "),
        DELETE("DELETE FROM orders WHERE id = ? "),
        FIND_ALL("SELECT id, customer_id, date, price, status FROM orders"),
        FIND_BY_CUSTOMER("SELECT id, customer_id, date, price, status FROM orders WHERE customer_id = ? LIMIT ?,"+ROWS_ON_PAGE),
        FIND_10_ROWS("SELECT id, customer_id, date, price, status FROM orders LIMIT ?,"+ROWS_ON_PAGE);

        private final String query;

        SQL(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }
}
