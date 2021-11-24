package com.epam.shop.service.api;

import com.epam.shop.service.dto.Dto;
import java.util.List;

public interface Service<E extends Dto, K> {

    /**
     * Method to save dtoObject
     *
     * @param e object to save
     * @return saved object from DB
     */
    E create(E e);

    /**
     * Method to update dtoObject
     *
     * @param e object to update
     * @return updated object from DB
     */
    E update(E e);

    /**
     * Method to delete dtoObject
     *
     * @param e object to save
     * @return result of deleting
     */
    boolean delete(E e);

    /**
     * Method to get dtoObject
     *
     * @param id object id in DB
     * @return object from DB
     */
    E getById(K id);

    /**
     * Method to get dtoObject list
     *
     * @return dtoObject list from DB
     */
    List<E> getAll();
}
