package com.epam.shop.dao.impl;

import com.epam.shop.dao.api.OrderItemDao;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.entity.OrderItem;
import com.epam.shop.dao.exception.ConnectionException;
import com.epam.shop.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {
    private static final Logger logger = LogManager.getLogger(OrderItemDaoImpl.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String TABLE = "orders_items";
    private static final String ID_COLUMN = "id";
    private static final String ORDER_ID_COLUMN = "order_id";
    private static final String PRODUCT_ID_COLUMN = "product_id";
    private static final String QUANTITY_COLUMN = "quantity";
    private static final String COUNT_COLUMN = "count(product_id)";

    @Override
    public OrderItem save(OrderItem orderItem) {
        ResultSet resultSet = null;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL.SAVE.getQuery(), new String[] {ID_COLUMN})) {
            statement.setLong(1, orderItem.getOrderId());
            statement.setLong(2, orderItem.getProductId());
            statement.setInt(3, orderItem.getQuantity());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                orderItem.setId(resultSet.getInt(1));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("OrderItem wasn't saved");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return orderItem;
    }

    @Override
    public OrderItem findById(Long key) {
        OrderItem orderItem = null;
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_BY_ID.getQuery())){
            statement.setString(1, String.valueOf(key));
            resultSet  = statement.executeQuery();
            if(resultSet.next()){
                orderItem = new OrderItem();
                orderItem.setId(resultSet.getLong(ID_COLUMN));
                orderItem.setOrderId(resultSet.getLong(ORDER_ID_COLUMN));
                orderItem.setProductId(resultSet.getLong(PRODUCT_ID_COLUMN));
                orderItem.setQuantity(resultSet.getInt(QUANTITY_COLUMN));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("OrderItem wasn't found");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return orderItem;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.UPDATE.getQuery())) {
            statement.setInt(1, orderItem.getQuantity());
            statement.setLong(2, orderItem.getId());
            statement.executeUpdate() ;
        } catch (SQLException | ConnectionException e) {
            logger.error("OrderItem wasn't updated");
            throw new DaoException(e.getMessage(), e);
        }
        return orderItem;
    }

    @Override
    public boolean delete(OrderItem orderItem) {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.DELETE.getQuery())) {
            statement.setLong(1, orderItem.getOrderId());
            statement.setLong(2, orderItem.getProductId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionException e) {
            logger.error("Order wasn't deleted");
            throw new DaoException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> list = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.FIND_ALL.getQuery());
             ResultSet resultSet =statement.executeQuery()){
            while(resultSet.next()){
                long id  = resultSet.getLong(ID_COLUMN);
                long orderId  = resultSet.getLong(ORDER_ID_COLUMN);
                long productId  = resultSet.getLong(PRODUCT_ID_COLUMN);
                int quantity = resultSet.getInt(QUANTITY_COLUMN);
                list.add(new OrderItem(id,orderId,productId,quantity));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("List was not received");
            throw new DaoException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<OrderItem> findFromOrder(Long key) {
        List<OrderItem> list = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.FIND_ALL_FROM_ORDER.getQuery())){
            statement.setLong(1, key);
            ResultSet resultSet =statement.executeQuery();
            while(resultSet.next()){
                long id  = resultSet.getLong(ID_COLUMN);
                long orderId  = resultSet.getLong(ORDER_ID_COLUMN);
                long productId  = resultSet.getLong(PRODUCT_ID_COLUMN);
                int quantity = resultSet.getInt(QUANTITY_COLUMN);
                list.add(new OrderItem(id,orderId,productId,quantity));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("List was not received");
            throw new DaoException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<OrderItem> getQuantity() {
        List<OrderItem> list = new LinkedList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.GET_QUANTITY.getQuery());
             ResultSet resultSet =statement.executeQuery()){
            while(resultSet.next()){
                long productId  = resultSet.getLong(PRODUCT_ID_COLUMN);
                int quantity = resultSet.getInt(COUNT_COLUMN);
                list.add(new OrderItem(productId,quantity));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("List was not received");
            throw new DaoException(e.getMessage(), e);
        }
        return list;
    }

    private enum SQL {
        SAVE("INSERT INTO order_items ( order_id, product_id, quantity) VALUES ( ?, ?, ?) "),
        FIND_BY_ID("SELECT id, order_id, product_id, quantity FROM order_items WHERE id = ?"),
        UPDATE("UPDATE order_items SET quantity = ? WHERE id = ? "),
        DELETE("DELETE FROM order_items  WHERE order_id = ? AND product_id = ? ORDER BY id LIMIT 1"),
        FIND_ALL("SELECT id, order_id, product_id, quantity FROM order_items "),
        GET_QUANTITY("SELECT product_id, count(product_id) FROM order_items GROUP BY product_id ORDER BY count(product_id) DESC LIMIT 5"),
        FIND_ALL_FROM_ORDER("SELECT id, order_id, product_id, quantity FROM order_items WHERE order_id = ?");

        private final String query;

        SQL(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }
}
