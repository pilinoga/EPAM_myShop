package com.epam.shop.dao.impl;

import com.epam.shop.dao.api.Dao;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.entity.Role;
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

public class RoleDaoImpl implements Dao<Role,Integer> {
    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String TABLE = "roles";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";

    @Override
    public Role save(Role role) {
        ResultSet resultSet = null;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL.SAVE.getQuery(), new String[] {ID_COLUMN} )) {
            statement.setString(1, role.getName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                role.setId(resultSet.getInt(1));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("Role wasn't saved");
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
    public Role findById(Integer key) {
        Role role = null;
        ResultSet resultSet = null;
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_BY_ID.getQuery())){
            statement.setString(1, String.valueOf(key));
            resultSet  = statement.executeQuery();
            if(resultSet.next()){
                role = new Role();
                role.setId(resultSet.getLong(ID_COLUMN));
                role.setName(resultSet.getString(NAME_COLUMN));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("Role wasn't found");
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
    public Role update(Role role)  {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.UPDATE.getQuery())) {
            statement.setString(1, role.getName());
            statement.setDouble(2, role.getId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.error("Role wasn't updated");
            throw new DaoException(e.getMessage(), e);
        }
        return role;
    }

    @Override
    public boolean delete(Role role) {
        boolean result;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL.DELETE.getQuery())) {
            statement.setString(1, role.getName());
            result = statement.executeUpdate() > 0;
        } catch (SQLException | ConnectionException e) {
            logger.error("Role wasn't deleted");
            throw new DaoException(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        try ( Connection connection = pool.takeConnection();
              PreparedStatement statement = connection.prepareStatement(SQL.FIND_ALL.getQuery());
              ResultSet resultSet =statement.executeQuery()){
            while(resultSet.next()){
                int id  = resultSet.getInt(ID_COLUMN);
                String name = resultSet.getString(NAME_COLUMN);
                list.add(new Role(id,name));
            }
        } catch (SQLException | ConnectionException e) {
            logger.error("List was not received");
            throw new DaoException(e.getMessage(), e);
        }
        return list;
    }

    private enum SQL {
        SAVE("INSERT INTO roles (name) VALUES (?) "),
        FIND_BY_ID("SELECT id, name FROM roles  WHERE id = ? "),
        UPDATE("UPDATE roles SET name = ? WHERE name = ? "),
        DELETE("DELETE FROM roles WHERE name = ? "),
        FIND_ALL("SELECT id,name FROM roles");

        private final String query;

        SQL(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }
}
