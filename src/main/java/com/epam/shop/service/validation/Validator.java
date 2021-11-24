package com.epam.shop.service.validation;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.service.dto.Dto;

public interface Validator<T extends Dto> {

    /**
     * Method for context validation
     *
     * @param context contains parameters for validation
     * @return result of validation
     */
    boolean isValid(RequestContext context);

    /**
     * Method for dto validation
     *
     * @param t contains fields for validation
     * @return result of validation
     */
    boolean isValid(T t);
}
