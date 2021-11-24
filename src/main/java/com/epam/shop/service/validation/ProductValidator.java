package com.epam.shop.service.validation;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.service.dto.ProductDto;

/**
 * The name must contain only letters and numbers.
 * The description must contain at least 1 characters and no more than 50 characters.
 * The price must contain only numbers and no more than two characters after the dot.
 */

public class ProductValidator implements Validator<ProductDto>{
    private static final String NAME_PARAMETER = "name";
    private static final String DESCRIPTION_PARAMETER = "description";
    private static final String PRICE_PARAMETER = "price";
    private static final String NAME_REGEX = "^[a-zA-Z0-9_ ]*$";
    private static final String DESCRIPTION_REGEX = "^.{1,50}$";
    private static final String PRICE_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";

    /**
     * Method for context validation
     *
     * @param context contains parameters for validation
     * @return result of validation
     */
    @Override
    public boolean isValid(RequestContext context) {
        String name = context.getParameter(NAME_PARAMETER);
        String description = context.getParameter(DESCRIPTION_PARAMETER);
        String price = context.getParameter(PRICE_PARAMETER);
        return name.matches(NAME_REGEX) &&
                description.matches(DESCRIPTION_REGEX) &&
                price.matches(PRICE_REGEX);
    }

    /**
     * Method for product validation
     *
     * @param product contains fields for validation
     * @return result of validation
     */
    @Override
    public boolean isValid(ProductDto product) {
        String name = product.getName();
        String description = product.getDescription();
        String price = String.valueOf(product.getPrice());
        return name.matches(NAME_REGEX) &&
                description.matches(DESCRIPTION_REGEX) &&
                price.matches(PRICE_REGEX);
    }
}
