package com.epam.shop.service.converter;

import com.epam.shop.dao.entity.Product;
import com.epam.shop.service.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProductConverterTest {
    private static ProductDto productDto;
    private static Product product;
    private static Converter<ProductDto, Product> converter;
    private static final String NAME = "name";
    private static final int ID = 1;
    private static final String DESCRIPTION = "description description description";
    private static final double PRICE = 100.25;
    private static final String SPECIFICATION = "specification";

    @BeforeAll
    static void setUp()  {
        converter = new ProductConverter();
        product = new Product(ID,NAME,DESCRIPTION,PRICE,SPECIFICATION);
        productDto = new ProductDto(ID,NAME,DESCRIPTION,PRICE,SPECIFICATION);
    }

    @Test
    void shouldCovertFromProductToProductDto(){
        ProductDto actual = converter.convert(product);
        Assertions.assertEquals(productDto,actual);
    }

    @Test
    void shouldCovertFromProductDtoToProduct(){
        Product actual = converter.convert(productDto);
        Assertions.assertEquals(product,actual);
    }
}
