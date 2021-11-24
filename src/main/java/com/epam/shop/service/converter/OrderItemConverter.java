package com.epam.shop.service.converter;

import com.epam.shop.dao.entity.OrderItem;
import com.epam.shop.service.dto.OrderItemDto;

public class OrderItemConverter implements Converter<OrderItemDto,OrderItem>{

    /**
     * Method to convert from OrderItem to OrderItemDto
     *
     * @param orderItem entity for converting
     * @return dto
     */
    @Override
    public OrderItemDto convert(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrderId(orderItem.getOrderId());
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }

    /**
     * Method to convert from OrderItemDto to OrderItem
     *
     * @param orderItemDto dto for converting
     * @return entity
     */
    @Override
    public OrderItem convert(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setOrderId(orderItemDto.getOrderId());
        orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }
}
