package com.epam.shop.service.dto;

import java.util.Objects;

public class ProductDto implements Dto{
    private long id;
    private String name;
    private String description;
    private double price;
    private String specification;

    public ProductDto(){}

    public ProductDto(String name, String description, double price, String specification) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.specification=specification;
    }

    public ProductDto(long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductDto(long id, String name, String description, double price, String specification) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.specification = specification;
    }

    public ProductDto(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                name.equals(that.name) &&
                Objects.equals(description, that.description)&&
                specification.equals(that.specification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", specification='" + specification + '\'' +
                ", price=" + price +
                '}';
    }
}
