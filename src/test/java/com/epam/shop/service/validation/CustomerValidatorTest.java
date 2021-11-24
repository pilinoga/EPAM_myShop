package com.epam.shop.service.validation;

import com.epam.shop.service.dto.CustomerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CustomerValidatorTest {
    private static Validator<CustomerDto> validator;
    private static CustomerDto customer;
    private static final String FIRST_NAME= "kirill";
    private static final String LAST_NAME = "pilinoga";
    private static final String EMAIL_PARAMETER = "email@email.com";
    private static final long PHONE_NUMBER = 331112233;
    private static final double CARD_BALANCE = 500.80;

    @BeforeAll
    static void setUp(){
        validator = new CustomerValidator();
        customer = new CustomerDto(FIRST_NAME,LAST_NAME,EMAIL_PARAMETER,PHONE_NUMBER,CARD_BALANCE,false);
    }

    @Test
    void shouldValidateCustomerFieldsPositive(){
        boolean result = validator.isValid(customer);
        Assertions.assertTrue(result);
    }

    @Test
    void shouldValidateCustomerFieldsNegative(){
        customer.setFirstName("");
        customer.setEmail("email");
        boolean result = validator.isValid(customer);
        Assertions.assertFalse(result);
    }
}
