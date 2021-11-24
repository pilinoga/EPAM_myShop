package com.epam.shop.service.validation;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.service.dto.CustomerDto;

/**
 * The name must contain only letters and at least 2 characters and no more than 10 characters
 * The balance must contain only numbers and no more than two characters after the dot.
 * The phone number must contain only numbers;+,(,) characters.
 * The email must contain @.
 */

public class CustomerValidator implements Validator<CustomerDto>{
    private static final String NAME_REGEX = "^[a-zA-Z]\\w{2,10}$";
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    private static final String PHONE_NUMBER_REGEX = "^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- ()]*$";
    private static final String CARD_BALANCE_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    private static final String FIRST_NAME_PARAMETER = "first_name";
    private static final String LAST_NAME_PARAMETER = "last_name";
    private static final String EMAIL_PARAMETER = "email";
    private static final String PHONE_NUMBER_PARAMETER = "phone_number";
    private static final String CARD_BALANCE_PARAMETER = "card_balance";

    /**
     * Method for context validation
     *
     * @param context contains parameters for validation
     * @return result of validation
     */
    @Override
    public boolean isValid(RequestContext context) {
        String firstName = context.getParameter(FIRST_NAME_PARAMETER);
        String lastName = context.getParameter(LAST_NAME_PARAMETER);
        String email = context.getParameter(EMAIL_PARAMETER);
        String phoneNumber = context.getParameter(PHONE_NUMBER_PARAMETER);
        String cardBalance = context.getParameter(CARD_BALANCE_PARAMETER);
        return firstName.matches(NAME_REGEX) &&
                lastName.matches(NAME_REGEX) &&
                email.matches(EMAIL_REGEX) &&
                phoneNumber.matches(PHONE_NUMBER_REGEX) &&
                cardBalance.matches(CARD_BALANCE_REGEX);
    }

    /**
     * Method for customer validation
     *
     * @param customer contains fields for validation
     * @return result of validation
     */
    @Override
    public boolean isValid(CustomerDto customer) {
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String email = customer.getEmail();
        String phoneNumber = String.valueOf(customer.getPhoneNumber());
        String cardBalance = String.valueOf(customer.getCardBalance());
        return firstName.matches(NAME_REGEX) &&
                lastName.matches(NAME_REGEX) &&
                email.matches(EMAIL_REGEX) &&
                phoneNumber.matches(PHONE_NUMBER_REGEX) &&
                cardBalance.matches(CARD_BALANCE_REGEX);
    }
}
