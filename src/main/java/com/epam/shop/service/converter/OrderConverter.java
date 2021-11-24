package com.epam.shop.service.converter;

import com.epam.shop.dao.entity.Order;
import com.epam.shop.service.dto.OrderDto;

public class OrderConverter implements Converter<OrderDto,Order>{

    /**
     * Method to convert from Order to OrderDto
     *
     * @param order entity for converting
     * @return dto
     */
    @Override
    public OrderDto convert(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setPrice(order.getPrice());
        orderDto.setStatus(order.getStatus());
        return orderDto;
    }

    /**
     * Method to convert from OrderDto to Order
     *
     * @param orderDto dto for converting
     * @return entity
     */
    @Override
    public Order convert(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomerId(orderDto.getCustomerId());
        order.setOrderDate(orderDto.getOrderDate());
        order.setPrice(orderDto.getPrice());
        order.setStatus(orderDto.getStatus());
        return order;
    }
}
