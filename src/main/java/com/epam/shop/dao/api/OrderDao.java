package com.epam.shop.dao.api;

import com.epam.shop.dao.entity.Order;
import java.util.List;

public interface OrderDao extends Dao<Order,Long>{

    /**
     * Method for searching customer orders to page
     *
     * @param key order number
     * @param pageNumber  page on website
     * @return orders list
     */
    List<Order> findForCustomer(Long key,int pageNumber);

    /**
     * Method for searching orders to page
     *
     * @param pageNumber number of page on website
     * @return order list
     */
    List<Order> findRowsForPage(int pageNumber);
}
