package com.epam.shop.dao.api;

import com.epam.shop.dao.entity.OrderItem;
import java.util.List;

public interface OrderItemDao extends Dao<OrderItem,Long>{

    /**
     * Method to find item from order
     *
     * @param key order id in DB
     * @return item list for order
     */
    List<OrderItem> findFromOrder(Long key);

    /**
     * Method to find item quantity
     *
     * @return list of quantity for each item
     */
    List<OrderItem> getQuantity();
}
