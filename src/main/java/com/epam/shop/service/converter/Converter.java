package com.epam.shop.service.converter;

import com.epam.shop.dao.entity.Entity;
import com.epam.shop.service.dto.Dto;

public interface Converter <D extends Dto, E extends Entity>{

    /**
     * Method to convert from entity to dto
     *
     * @param e entity for converting
     * @return dto
     */
    D convert(E e);

    /**
     * Method to convert from dto to entity
     *
     * @param d dto for converting
     * @return entity
     */
    E convert(D d);
}
