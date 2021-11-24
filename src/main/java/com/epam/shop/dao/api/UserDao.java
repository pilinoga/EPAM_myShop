package com.epam.shop.dao.api;

import com.epam.shop.dao.Role;
import com.epam.shop.dao.entity.User;

public interface UserDao extends Dao<User,Integer> {

    /**
     * Method to check user
     *
     * @param user contains login and password for searching
     * @return result of searching
     */
    boolean findByLoginAndPassword(User user);

    /**
     * Method to receive role of user fromDB
     *
     * @param user contains login for searching
     * @return role of user
     */
    Role getRoleByLogin(User user);

    /**
     * Method to check for login uniqueness
     *
     * @param user entity that contains login for checking uniqueness
     * @return result of checking
     */
    boolean isUniqueLogin(User user);

    /**
     * Method to find user in DB by login
     *
     * @param login login of user
     * @return entity from DB
     */
    User findByLogin(String login);
}
