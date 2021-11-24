package com.epam.shop.service.api;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.service.dto.ProductDto;
import java.util.List;

public interface ProductService extends Service<ProductDto,Long>{

    /**
     * Method for product validation
     *
     * @param product contains fields for validation
     * @return result of validation
     */
    boolean isValid(ProductDto product);

    /**
     * Method for context validation
     *
     * @param context contains parameters for validation
     * @return result of validation
     */
    boolean isValid(RequestContext context);

    /**
     * Method for getting list of top sales product
     *
     * @return product list
     */
    List<ProductDto> getTopSales();

    /**
     * Method for getting products on page
     *
     * @param pageNumber number of page on website
     * @return product list
     */
    List<ProductDto> getRowsForPage(int pageNumber);
}
