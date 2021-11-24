package com.epam.shop.dao;

import com.epam.shop.dao.api.CustomerDao;
import com.epam.shop.dao.api.Dao;
import com.epam.shop.dao.api.OrderDao;
import com.epam.shop.dao.api.OrderItemDao;
import com.epam.shop.dao.api.ProductDao;
import com.epam.shop.dao.api.UserDao;
import com.epam.shop.dao.entity.Role;
import com.epam.shop.dao.impl.CustomerDaoImpl;
import com.epam.shop.dao.impl.OrderDaoImpl;
import com.epam.shop.dao.impl.OrderItemDaoImpl;
import com.epam.shop.dao.impl.ProductDaoImpl;
import com.epam.shop.dao.impl.RoleDaoImpl;
import com.epam.shop.dao.impl.UserDaoImpl;

public class DaoFactory {
    private final UserDao userDao;
    private final CustomerDao customerDao;
    private final ProductDao productDao;
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final Dao<Role,Integer> roleDao;

    private DaoFactory() {
        userDao = new UserDaoImpl();
        customerDao = new CustomerDaoImpl();
        productDao = new ProductDaoImpl();
        orderDao = new OrderDaoImpl();
        orderItemDao = new OrderItemDaoImpl();
        roleDao = new RoleDaoImpl();
    }

    public Dao<Role,Integer> getRoleDao() {
        return roleDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public OrderItemDao getOrderItemDao() {
        return orderItemDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public static DaoFactory getInstance() {
        return Singleton.INSTANCE;
    }

    private static class Singleton{
        private static final DaoFactory INSTANCE = new DaoFactory();
    }

}