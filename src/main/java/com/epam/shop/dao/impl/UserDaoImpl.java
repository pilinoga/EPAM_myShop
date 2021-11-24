package com.epam.shop.dao.impl;

import com.epam.shop.dao.Role;
import com.epam.shop.dao.api.UserDao;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.entity.User;
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

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String TABLE = "users";
    private static final String ID_COLUMN = "id";
    private static final String ROLE_ID_COLUMN = "role_id";
    private static final String LOGIN_COLUMN = "login";
    private static final String PASSWORD_COLUMN = "password";

    @Override
    public User save(User user) {
        ResultSet resultSet = null;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL.SAVE.getQuery(), new String[] {ID_COLUMN})) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setRoleId(Role.CUSTOMER.getIdRole());
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("User wasn't saved");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return user;
    }

    @Override
    public User findById(Integer key) {
        User user = null;
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_BY_ID.getQuery())){
            statement.setString(1, String.valueOf(key));
            resultSet  = statement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong(ID_COLUMN));
                user.setRoleId(resultSet.getInt(ROLE_ID_COLUMN));
                user.setLogin(resultSet.getString(LOGIN_COLUMN));
                user.setPassword(resultSet.getString(PASSWORD_COLUMN));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("User wasn't found");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return user;
    }

    @Override
    public User update(User user) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.UPDATE.getQuery())) {
            statement.setString(1, user.getPassword());
            statement.setLong(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("User wasn't updated");
            throw new DaoException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public boolean delete(User user) {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.DELETE.getQuery())) {
            statement.setLong(1, user.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionException e) {
            logger.error("User wasn't deleted");
            throw new DaoException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.FIND_ALL.getQuery());
             ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                String login = resultSet.getString(LOGIN_COLUMN);
                String password = resultSet.getString(PASSWORD_COLUMN);
                list.add(new User(login,password));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("List was not received");
            throw new DaoException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public boolean findByLoginAndPassword(User user) {
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_BY_LOGIN_AND_PASSWORD.getQuery())){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            resultSet  = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException | ConnectionException e) {
            logger.error("User wasn't found");
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
    public Role getRoleByLogin(User user) {
        Role role = Role.UNKNOWN;
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.GET_ROLE.getQuery())){
            statement.setString(1, user.getLogin());
            resultSet  = statement.executeQuery();
            if(resultSet.next()){
                 role = Role.getRole(resultSet.getInt(ROLE_ID_COLUMN));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("User's role was not received");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return role;
    }

    @Override
    public boolean isUniqueLogin(User user){
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_BY_LOGIN.getQuery())){
            statement.setString(1, user.getLogin());
            resultSet  = statement.executeQuery();
            return !resultSet.next();
        } catch (SQLException | ConnectionException e) {
            logger.error("User's login was not received");
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
    public User findByLogin(String login) {
        User user = null;
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.GET_BY_LOGIN.getQuery())){
            statement.setString(1, login);
            resultSet  = statement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong(ID_COLUMN));
                user.setRoleId(resultSet.getInt(ROLE_ID_COLUMN));
                user.setLogin(resultSet.getString(LOGIN_COLUMN));
                user.setPassword(resultSet.getString(PASSWORD_COLUMN));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("User's login was not found");
            throw new DaoException(e.getMessage(), e);
        }finally {
            try {
                if (resultSet!= null){ resultSet.close();}
            } catch (SQLException e) {
                logger.error("ResultSet wasn't closed");
            }
        }
        return user;
    }

    private enum SQL {
        SAVE("INSERT INTO users (login, password) VALUES ( ?, ?)"),
        FIND_BY_ID("SELECT id, role_id, login, password FROM users WHERE id = ?"),
        GET_BY_LOGIN("SELECT id, role_id, login, password FROM users WHERE login = ?"),
        FIND_BY_LOGIN_AND_PASSWORD("SELECT login, password FROM users WHERE login = ? AND password = ?"),
        FIND_BY_LOGIN("SELECT login FROM users WHERE login = ?"),
        UPDATE("UPDATE users SET password = ? WHERE id = ? "),
        DELETE("DELETE FROM users WHERE id = ? "),
        FIND_ALL("SELECT login, password FROM users WHERE role_id=2"),
        GET_ROLE("SELECT role_id FROM users WHERE login = ?");

        private final String query;

        SQL(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }

}
