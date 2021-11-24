package com.epam.shop.service.converter;

import com.epam.shop.dao.entity.Product;
import com.epam.shop.service.dto.ProductDto;

public class ProductConverter implements Converter<ProductDto,Product>{

    /**
     * Method to convert from Product to ProductDto
     *
     * @param product entity for converting
     * @return dto
     */
    @Override
    public ProductDto convert(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setSpecification(product.getSpecification());
        return productDto;
    }

    /**
     * Method to convert from ProductDto to Product
     *
     * @param productDto dto for converting
     * @return entity
     */
    @Override
    public Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setSpecification(productDto.getSpecification());
        return product;
    }
}
