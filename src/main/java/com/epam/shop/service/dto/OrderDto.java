package com.epam.shop.service.dto;

import java.sql.Date;
import java.util.Objects;

public class OrderDto implements Dto{
    private long id;
    private long customerId;
    private Date orderDate;
    private double price;
    private boolean status;

    public OrderDto() {
    }

    public OrderDto(long customerId, Date orderDate, double price, boolean status) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.price = price;
        this.status = status;
    }

    public OrderDto(long id, long customerId, Date orderDate, double price, boolean status) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.price = price;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return id == orderDto.id &&
                customerId == orderDto.customerId &&
                Double.compare(orderDto.price, price) == 0 &&
                status == orderDto.status &&
                Objects.equals(orderDate, orderDto.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, orderDate, price, status);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}


