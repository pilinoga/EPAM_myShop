package com.epam.shop.dao.entity;

public class Product implements Entity {
    private long id;
    private String name;
    private String description;
    private double price;
    private String specification;

    public Product(){}

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(long id, String name, String description, double price,String specification) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.specification = specification;
    }

    @Override
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
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                name.equals(product.name) &&
                description.equals(product.description)&&
                specification.equals(product.specification);
    }

    @Override
    public int hashCode() {
        final int hashFactor = 31;
        int hash = 1;
        hash = hashFactor * hash + (int) id;
        hash = hashFactor * hash + (name == null ? 0 : name.hashCode());
        hash = hashFactor * hash + (description == null ? 0 : description.hashCode());
        hash = hashFactor * hash + (int)price;
        hash = hashFactor * hash + (specification == null ? 0 : specification.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", specification='" + specification + '\'' +
                ", price=" + price +
                '}';
    }
}
