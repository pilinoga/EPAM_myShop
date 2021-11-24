package com.epam.shop.dao.impl;

import com.epam.shop.dao.api.CustomerDao;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.entity.Customer;
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

public class CustomerDaoImpl implements CustomerDao {
    private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String TABLE = "customers";
    private static final String ID_COlUMN = "id";
    private static final String FIRST_NAME_COlUMN = "first_name";
    private static final String LAST_NAME_COlUMN = "last_name";
    private static final String EMAIL_COlUMN = "email";
    private static final String PHONE_NUMBER_COlUMN = "phone_number";
    private static final String CARD_BALANCE_COlUMN = "card_balance";
    private static final String STATUS_COlUMN = "status";
    private static final int ROWS_ON_PAGE = 10;

    @Override
    public Customer save(Customer customer){
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.SAVE.getQuery())) {
            statement.setLong(1, customer.getId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getEmail());
            statement.setLong(5, customer.getPhoneNumber());
            statement.setDouble(6, customer.getCardBalance());
            statement.setBoolean(7, customer.isBlocked());
            statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Customer wasn't saved");
            throw new DaoException(e.getMessage(), e);
        }
        return customer;

    }

    @Override
    public Customer findById(Long key) {
        Customer customer = null;
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement =
                      connection.prepareStatement(SQL.FIND_BY_ID.getQuery())){
            statement.setString(1, String.valueOf(key));
            resultSet  = statement.executeQuery();
            if(resultSet.next()){
                customer = new Customer();
                customer.setId(resultSet.getLong(ID_COlUMN));
                customer.setFirstName(resultSet.getString(FIRST_NAME_COlUMN));
                customer.setLastName(resultSet.getString(LAST_NAME_COlUMN));
                customer.setEmail(resultSet.getString(EMAIL_COlUMN));
                customer.setPhoneNumber(Long.parseLong(resultSet.getString(PHONE_NUMBER_COlUMN)));
                customer.setCardBalance(resultSet.getDouble(CARD_BALANCE_COlUMN));
                customer.setBlocked(resultSet.getBoolean(STATUS_COlUMN));
            }
            return customer;
        } catch (SQLException | ConnectionException e) {
            logger.error("Customer wasn't found");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
    }

    @Override
    public Customer update(Customer customer) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.UPDATE.getQuery())) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setLong(4, customer.getPhoneNumber());
            statement.setDouble(5, customer.getCardBalance());
            statement.setBoolean(6, customer.isBlocked());
            statement.setLong(7, customer.getId());
            statement.executeUpdate() ;
        } catch (SQLException | ConnectionException e) {
            logger.error("Customer wasn't updated");
            throw new DaoException(e.getMessage(), e);
        }
        return customer;
    }

    @Override
    public boolean delete(Customer customer)  {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.DELETE.getQuery())) {
            statement.setLong(1, customer.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionException e) {
            logger.error("Customer wasn't deleted");
            throw new DaoException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.FIND_ALL.getQuery());
             ResultSet resultSet =statement.executeQuery()){
            while(resultSet.next()){
                long id  = resultSet.getLong(ID_COlUMN);
                String firstName = resultSet.getString(FIRST_NAME_COlUMN);
                String lastName = resultSet.getString(LAST_NAME_COlUMN);
                String email = resultSet.getString(EMAIL_COlUMN);
                long phone = resultSet.getLong(PHONE_NUMBER_COlUMN);
                double cardBalance = resultSet.getDouble(CARD_BALANCE_COlUMN);
                boolean blocked = resultSet.getBoolean(STATUS_COlUMN);
                list.add(new Customer(id,firstName,lastName,email,phone,cardBalance,blocked));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("List was not received");
            throw new DaoException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Customer> findRowsForPage(int pageNumber){
        List<Customer> list = new ArrayList<>();
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_10_ROWS.getQuery())){
            statement.setInt(1, (pageNumber-1)*ROWS_ON_PAGE);
            resultSet  = statement.executeQuery();
            while(resultSet.next()){
                long id  = resultSet.getLong(ID_COlUMN);
                String firstName = resultSet.getString(FIRST_NAME_COlUMN);
                String lastName = resultSet.getString(LAST_NAME_COlUMN);
                String email = resultSet.getString(EMAIL_COlUMN);
                long phone = resultSet.getLong(PHONE_NUMBER_COlUMN);
                double cardBalance = resultSet.getDouble(CARD_BALANCE_COlUMN);
                boolean blocked = resultSet.getBoolean(STATUS_COlUMN);
                list.add(new Customer(id,firstName,lastName,email,phone,cardBalance,blocked));
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

    @Override
    public boolean findInfo(long id) {
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_INFO.getQuery())){
            statement.setString(1, String.valueOf(id));
            resultSet  = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException | ConnectionException e) {
            logger.error("Customer info wasn't found");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
    }


    private enum SQL {
        SAVE("INSERT INTO customers (id, first_name, last_name, email, phone_number, card_balance, status) VALUES (?, ?, ?, ?, ?, ?, ?) "),
        FIND_BY_ID("SELECT id, first_name, last_name, email, phone_number, card_balance, status FROM customers WHERE id = ? "),
        UPDATE("UPDATE customers SET  first_name = ?, last_name = ?, email = ?, phone_number = ?, card_balance = ?, status = ? WHERE id = ?"),
        DELETE("DELETE FROM customers WHERE id = ? "),
        FIND_ALL("SELECT id, first_name, last_name, email, phone_number, card_balance, status FROM customers"),
        FIND_INFO("SELECT id, first_name, last_name, email, phone_number, card_balance, status FROM customers WHERE id = ?"),
        FIND_10_ROWS("SELECT id, first_name, last_name, email, phone_number, card_balance, status FROM customers LIMIT ?,"+ROWS_ON_PAGE);

        private final String query;

        SQL(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }
}
