package com.epam.shop.dao.entity;

import java.sql.Date;
import java.util.Objects;

public class Order implements Entity {
    private long id;
    private long customerId;
    private Date orderDate;
    private double price;
    private boolean status;

    public Order() {
    }

    public Order(long id, long customerId, Date orderDate, double price, boolean status) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.price = price;
        this.status = status;
    }

    @Override
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
        Order order = (Order) o;
        return id == order.id &&
                customerId == order.customerId &&
                Double.compare(order.price, price) == 0 &&
                status == order.status &&
                Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, orderDate, price, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
