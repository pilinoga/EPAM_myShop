package com.epam.shop.service.api;

import com.epam.shop.service.dto.OrderDto;
import com.epam.shop.service.dto.ProductDto;
import java.util.List;

public interface OrderService extends Service<OrderDto,Long>{

    /**
     * Method for getting list of products for order
     *
     * @param orderId number of specific order
     * @return product list for order
     */
    List<ProductDto> getProducts(Long orderId);

    /**
     * Method for getting prices for order
     *
     * @param products order product list
     * @return order price
     */
    Double getOrderPrice(List<ProductDto> products);

    /**
     * Method for getting customer orders on page
     *
     * @param key order number
     * @param pageNumber  page on website
     * @return orders list
     */
    List<OrderDto> getForCustomer(Long key, int pageNumber);

    /**
     * Method for getting orders on page
     *
     * @param pageNumber number of page on website
     * @return orders list
     */
    List<OrderDto> getRowsForPage(int pageNumber);
}
