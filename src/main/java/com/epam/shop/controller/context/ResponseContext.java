package com.epam.shop.controller.context;

public interface ResponseContext {

    /**
     * Method to get jsp page
     *
     * @return path to jsp page
     */
    String getPage();

    /**
     * Method to check redirect
     *
     * @return result of checking
     */
    boolean isRedirect();
}
