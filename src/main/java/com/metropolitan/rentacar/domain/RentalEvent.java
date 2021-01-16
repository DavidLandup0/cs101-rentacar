package com.metropolitan.rentacar.domain;

import org.springframework.data.mongodb.core.mapping.Field;

public class RentalEvent {

    @Field("customer")
    private Customer customer;

    @Field("car")
    private Car car;

    @Field("length_of_rent")
    private int lengthOfRent;

    @Field("total_price")
    private int totalPrice;

    public RentalEvent(){}

    public RentalEvent(Customer customer, Car car, int lengthOfRent, int totalPrice) {
        this.customer = customer;
        this.car = car;
        this.lengthOfRent = lengthOfRent;
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getLengthOfRent() {
        return lengthOfRent;
    }

    public void setLengthOfRent(int lengthOfRent) {
        this.lengthOfRent = lengthOfRent;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "RentalEvent{" +
                "customer=" + customer +
                ", car=" + car +
                ", lengthOfRent=" + lengthOfRent +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
