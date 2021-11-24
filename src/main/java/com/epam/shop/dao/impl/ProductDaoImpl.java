package com.epam.shop.dao.impl;

import com.epam.shop.dao.api.ProductDao;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.entity.Product;
import com.epam.shop.dao.exception.ConnectionException;
import com.epam.shop.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final Logger logger = LogManager.getLogger(ProductDaoImpl.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String TABLE = "products";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String SPECIFICATION_COLUMN = "specification";
    private static final String PRICE_COLUMN = "price";
    private static final int ROWS_ON_PAGE = 10;

    @Override
    public Product save(Product product) {
        ResultSet resultSet = null;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL.SAVE.getQuery(), new String[] {ID_COLUMN} )) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getSpecification());
            statement.executeUpdate() ;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getInt(1));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("Product wasn't saved");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return product;
    }

    @Override
    public Product findById(Long key) {
        Product product = null;
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_BY_ID.getQuery())){
            statement.setString(1, String.valueOf(key));
            resultSet  = statement.executeQuery();
            if(resultSet.next()){
                product = new Product();
                product.setId(resultSet.getLong(ID_COLUMN));
                product.setName(resultSet.getString(NAME_COLUMN));
                product.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
                product.setPrice(resultSet.getDouble(PRICE_COLUMN));
                product.setSpecification(resultSet.getString(SPECIFICATION_COLUMN));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("Product wasn't found");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.UPDATE.getQuery())) {
            statement.setDouble(1, product.getPrice());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getName());
            statement.setString(4, product.getSpecification());
            statement.setLong(5, product.getId());
            statement.executeUpdate() ;
        } catch (SQLException | ConnectionException e) {
            logger.error("Product wasn't updated");
            throw new DaoException(e.getMessage(), e);
        }
        return product;
    }

    @Override
    public boolean delete(Product product) {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.DELETE.getQuery())) {
            statement.setLong(1, product.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionException e) {
            logger.error("Product wasn't deleted");
            throw new DaoException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.FIND_ALL.getQuery());
             ResultSet resultSet =statement.executeQuery()){
            while(resultSet.next()){
                long id  = resultSet.getLong(ID_COLUMN);
                String name = resultSet.getString(NAME_COLUMN);
                String description = resultSet.getString(DESCRIPTION_COLUMN);
                String specification = resultSet.getString(SPECIFICATION_COLUMN);
                double price = resultSet.getDouble(PRICE_COLUMN);
                list.add(new Product(id,name,description,price,specification));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("List was not received");
            throw new DaoException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Product> findRowsForPage(int pageNumber) {
        List<Product> list = new ArrayList<>();
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_10_ROWS.getQuery())){
            statement.setInt(1, (pageNumber-1)*ROWS_ON_PAGE);
            resultSet  = statement.executeQuery();
            while(resultSet.next()){
                long id  = resultSet.getLong(ID_COLUMN);
                String name = resultSet.getString(NAME_COLUMN);
                String description = resultSet.getString(DESCRIPTION_COLUMN);
                String specification = resultSet.getString(SPECIFICATION_COLUMN);
                double price = resultSet.getDouble(PRICE_COLUMN);
                list.add(new Product(id,name,description,price,specification));
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
        SAVE("INSERT INTO products (name, description, price, specification) VALUES ( ?, ?, ?, ?)"),
        FIND_BY_ID("SELECT id, name, description, price, specification FROM products WHERE id = ?"),
        UPDATE("UPDATE products SET price = ?, description = ?, name = ?, specification = ?  WHERE id = ? "),
        DELETE("DELETE FROM products WHERE id = ? "),
        FIND_ALL("SELECT id, name, description, price, specification FROM products"),
        FIND_10_ROWS("SELECT id, name, description, price, specification FROM products LIMIT ?,"+ROWS_ON_PAGE);

        private final String query;

        SQL(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }
}
