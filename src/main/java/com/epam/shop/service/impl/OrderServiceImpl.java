package com.epam.shop.service.impl;

import com.epam.shop.dao.DaoFactory;
import com.epam.shop.dao.api.OrderDao;
import com.epam.shop.dao.entity.Order;
import com.epam.shop.service.api.OrderItemService;
import com.epam.shop.service.api.OrderService;
import com.epam.shop.service.api.ProductService;
import com.epam.shop.service.converter.Converter;
import com.epam.shop.service.converter.OrderConverter;
import com.epam.shop.service.dto.OrderDto;
import com.epam.shop.service.dto.OrderItemDto;
import com.epam.shop.service.dto.ProductDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final Converter<OrderDto, Order> converter;
    private final OrderDao orderDao;
    private final OrderItemService serviceItem = new OrderItemServiceImpl();
    private final ProductService serviceProduct = new ProductServiceImpl();

    public OrderServiceImpl() {
        converter = new OrderConverter();
        orderDao = DaoFactory.getInstance().getOrderDao();
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        Order order = orderDao.save(converter.convert(orderDto));
        return converter.convert(order);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        Order order =orderDao.update(converter.convert(orderDto));
        return converter.convert(order);
    }

    @Override
    public boolean delete(OrderDto orderDto) {
        return orderDao.delete(converter.convert(orderDto));
    }

    @Override
    public OrderDto getById(Long id) {
        return converter.convert(orderDao.findById(id));
    }

    @Override
    public List<OrderDto> getAll() {
        return orderDao.findAll().stream()
                .map(converter::convert).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProducts(Long orderId) {
        List<ProductDto> listProducts = new ArrayList<>();
        List<OrderItemDto> listItems = serviceItem.findFromOrder(orderId);
        for (OrderItemDto orderItem : listItems) {
            long id = orderItem.getProductId();
            ProductDto product = serviceProduct.getById(id);
            listProducts.add(product);
        }
        return listProducts;
    }

    @Override
    public Double getOrderPrice(List<ProductDto> products) {
        return products.stream().mapToDouble(ProductDto::getPrice).sum();
    }

    @Override
    public List<OrderDto> getForCustomer(Long key, int pageNumber) {
        return orderDao.findForCustomer(key, pageNumber).stream()
                .map(converter::convert).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getRowsForPage(int pageNumber){
        return orderDao.findRowsForPage(pageNumber).stream()
                .map(converter::convert).collect(Collectors.toList());
    }

}
