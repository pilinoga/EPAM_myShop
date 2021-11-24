package com.epam.shop.service.dto;

import java.util.Objects;

public class CustomerDto implements Dto{
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private double cardBalance;
    private boolean block;

    public CustomerDto(){}

    public CustomerDto(long id,  String firstName, String lastName,
                    String email, long phoneNumber, double cardBalance, boolean block) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cardBalance = cardBalance;
        this.block = block;
    }

    public CustomerDto(String firstName, String lastName, String email, long phoneNumber,
                       double cardBalance, boolean block) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cardBalance = cardBalance;
        this.block = block;
    }

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

    public boolean getBlock() {
        return block;
    }

    public void setBlock(boolean blocked) {
        this.block = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return id == that.id &&
                phoneNumber == that.phoneNumber &&
                Double.compare(that.cardBalance, cardBalance) == 0 &&
                block== that.block &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email,
                phoneNumber, cardBalance, block);
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", cardBalance=" + cardBalance +
                ", blocked=" + block +
                '}';
    }
}
