package com.epam.shop.dao.api;

import com.epam.shop.dao.entity.Product;
import java.util.List;

public interface ProductDao extends Dao<Product,Long>{

    /**
     * Method to find products to page
     *
     * @param pageNumber number of page on website
     * @return product list
     */
    List<Product> findRowsForPage(int pageNumber);
}
