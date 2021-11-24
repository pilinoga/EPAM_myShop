package com.epam.shop.dao.api;

import com.epam.shop.dao.entity.Customer;
import java.util.List;

public interface CustomerDao extends Dao<Customer,Long>{

    /**
     * Method for finding customers to page
     *
     * @param pageNumber number of page on website
     * @return customer list
     */
    List<Customer> findRowsForPage(int pageNumber);

    /**
     * Method to find for customer information existence
     *
     * @param id key for searching customer
     * @return result of searching
     */
    boolean findInfo(long id);
}
