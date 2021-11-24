package com.epam.shop.dao.entity;

import java.util.Objects;

public class Customer implements Entity {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private double cardBalance;
    private boolean blocked;

    public Customer(){}

    public Customer(long id,  String firstName, String lastName,
                    String email, long phoneNumber, double cardBalance, boolean blocked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cardBalance = cardBalance;
        this.blocked = blocked;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                phoneNumber == customer.phoneNumber &&
                Double.compare(customer.cardBalance, cardBalance) == 0 &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        final int hashFactor = 31;
        int hash = 1;
        hash = hashFactor * hash + (int) id;
        hash = hashFactor * hash + (firstName == null ? 0 : firstName.hashCode());
        hash = hashFactor * hash + (lastName == null ? 0 : lastName.hashCode());
        hash = hashFactor * hash + (email == null ? 0 : email.hashCode());
        hash = hashFactor * hash + (int)phoneNumber;
        hash = hashFactor * hash + (int)cardBalance;
        return hash;

    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", cardBalance=" + cardBalance +
                ", blocked=" + blocked +
                '}';
    }
}


