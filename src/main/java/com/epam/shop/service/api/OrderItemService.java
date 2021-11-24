package com.epam.shop.service.api;

import com.epam.shop.service.dto.OrderItemDto;
import java.util.List;

public interface OrderItemService extends Service<OrderItemDto,Long>{

    /**
     * Method to get item from order
     *
     * @param key order id
     * @return item list for order
     */
    List<OrderItemDto> findFromOrder(Long key);

    /**
     * Method for getting item quantity
     *
     * @return list of quantity for each item
     */
    List<OrderItemDto> getQuantity();

}
