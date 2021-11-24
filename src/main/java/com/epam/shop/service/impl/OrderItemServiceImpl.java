package com.epam.shop.service.impl;

import com.epam.shop.dao.DaoFactory;
import com.epam.shop.dao.api.OrderItemDao;
import com.epam.shop.dao.entity.OrderItem;
import com.epam.shop.service.api.OrderItemService;
import com.epam.shop.service.converter.Converter;
import com.epam.shop.service.converter.OrderItemConverter;
import com.epam.shop.service.dto.OrderItemDto;
import java.util.List;
import java.util.stream.Collectors;

public class OrderItemServiceImpl implements OrderItemService {
    private final Converter<OrderItemDto, OrderItem> converter;
    private final OrderItemDao orderItemDao;

    public OrderItemServiceImpl() {
        converter= new OrderItemConverter();
        orderItemDao = DaoFactory.getInstance().getOrderItemDao();
    }

    @Override
    public OrderItemDto create(OrderItemDto itemDto) {
        OrderItem item = orderItemDao.save(converter.convert(itemDto));
        return converter.convert(item);
    }

    @Override
    public OrderItemDto update(OrderItemDto orderItemDto) {
        OrderItem item =orderItemDao.update(converter.convert(orderItemDto));
        return converter.convert(item);
    }

    @Override
    public boolean delete(OrderItemDto orderItemDto) {
        return orderItemDao.delete(converter.convert(orderItemDto));
    }

    @Override
    public OrderItemDto getById(Long id) {
        return converter.convert(orderItemDao.findById(id));
    }

    @Override
    public List<OrderItemDto> getAll() {
        return orderItemDao.findAll().stream()
                .map(converter::convert).collect(Collectors.toList());
    }


    @Override
    public List<OrderItemDto> findFromOrder(Long key) {
        return orderItemDao.findFromOrder(key).stream()
                .map(converter::convert).collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDto> getQuantity(){
        List<OrderItem> items = orderItemDao.getQuantity();
        return items.stream()
                .map(converter::convert).collect(Collectors.toList());
    }
}
