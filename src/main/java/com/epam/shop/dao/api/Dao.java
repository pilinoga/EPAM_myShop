package com.epam.shop.dao.api;

import com.epam.shop.dao.entity.Entity;
import java.util.List;

public interface Dao<E extends Entity, K> {
    /**
     * Method to save entity
     *
     * @param e entity to save
     * @return saved entity from DB
     */
    E save(E e);

    /**
     * Method to get entity
     *
     * @param key entity id in DB
     * @return entity from DB
     */
    E findById(K key);

    /**
     * Method to update entity
     *
     * @param e entity to save
     * @return updated entity from DB
     */
    E update(E e);

    /**
     * Method to delete entity
     *
     * @param e entity to save
     * @return result of deleting
     */
    boolean delete(E e);

    /**
     * Method to get entity list
     *
     * @return entity list from DB
     */
    List<E> findAll();
}

