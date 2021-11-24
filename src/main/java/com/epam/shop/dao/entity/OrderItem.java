package com.epam.shop.dao.entity;

public class OrderItem implements Entity {
    private long id;
    private long orderId;
    private long productId;
    private int quantity;

    public OrderItem() {}

    public OrderItem(long id, long orderId, long productId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderItem(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id == orderItem.id &&
                orderId == orderItem.orderId &&
                productId == orderItem.productId &&
                quantity == orderItem.quantity;
    }

    @Override
    public int hashCode() {
        final int hashFactor = 31;
        int hash = 1;
        hash = hashFactor * hash + (int) id;
        hash = hashFactor * hash + (int) orderId;
        hash = hashFactor * hash + (int) productId;
        hash = hashFactor * hash + quantity;
        return hash;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}


