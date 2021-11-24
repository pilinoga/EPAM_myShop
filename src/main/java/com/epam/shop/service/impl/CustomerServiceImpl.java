package com.epam.shop.service.impl;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.dao.api.CustomerDao;
import com.epam.shop.dao.DaoFactory;
import com.epam.shop.dao.entity.Customer;
import com.epam.shop.service.api.CustomerService;
import com.epam.shop.service.converter.Converter;
import com.epam.shop.service.converter.CustomerConverter;
import com.epam.shop.service.dto.CustomerDto;
import com.epam.shop.service.validation.CustomerValidator;
import com.epam.shop.service.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private final Converter<CustomerDto, Customer> converter;
    private final CustomerDao customerDao;
    private final Validator<CustomerDto> validator;

    public CustomerServiceImpl() {
        converter = new CustomerConverter();
        customerDao = DaoFactory.getInstance().getCustomerDao();
        validator = new CustomerValidator();
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = customerDao.save(converter.convert(customerDto));
        return converter.convert(customer);

    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        Customer customer = customerDao.update(converter.convert(customerDto));
        return converter.convert(customer);
    }

    @Override
    public boolean delete(CustomerDto customerDto) {
        return customerDao.delete(converter.convert(customerDto));
    }

    @Override
    public CustomerDto getById(Long id) {
        return converter.convert(customerDao.findById(id));
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerDao.findAll().stream()
                .map(converter::convert).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CustomerDto customer) {
        return validator.isValid(customer);
    }

    @Override
    public boolean isValid(RequestContext context) {
        return validator.isValid(context);
    }

    @Override
    public List<CustomerDto> getRowsForPage(int pageNumber){
        return customerDao.findRowsForPage(pageNumber).stream()
                .map(converter::convert).collect(Collectors.toList());
    }

    @Override
    public boolean isInfoExist(long id) {
        return customerDao.findInfo(id);
    }
}
