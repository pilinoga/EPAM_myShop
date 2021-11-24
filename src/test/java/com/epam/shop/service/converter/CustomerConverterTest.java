package com.epam.shop.service.converter;

import com.epam.shop.dao.entity.Customer;
import com.epam.shop.service.dto.CustomerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CustomerConverterTest {
    private static Customer customer;
    private static CustomerDto customerDto;
    private static Converter<CustomerDto,Customer> converter;
    private static final long ID = 87 ;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "test@email";
    private static final long PHONE_NUMBER = 331112233;
    private static final double CARD_BALANCE = 150.50;
    private static final boolean STATUS = false;

    @BeforeAll
    static void setUp(){
        converter =new CustomerConverter();
        customer = new Customer(ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,CARD_BALANCE,STATUS);
        customerDto = new CustomerDto(ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,CARD_BALANCE,STATUS);
    }

    @Test
    void shouldCovertFromCustomerToCustomerDto(){
        CustomerDto actual = converter.convert(customer);
        Assertions.assertEquals(customerDto,actual);
    }

    @Test
    void shouldCovertFromCustomerDtoToCustomer(){
        Customer actual = converter.convert(customerDto);
        Assertions.assertEquals(customer,actual);
    }
}
