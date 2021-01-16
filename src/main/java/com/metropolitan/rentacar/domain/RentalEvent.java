package com.metropolitan.rentacar.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

public class RentalEvent {

    @Field("customer")
    private Customer customer;

    @Field("car")
    private Car car;

    @Field("length_of_rent")
    private int lengthOfRent;

    @Field("total_price")
    private int totalPrice;

    @Field("rented_on")
    private Instant rentedOn = Instant.now();

    @Field("rented_to")
    private Instant rentedTo;

    public RentalEvent(){}

    public RentalEvent(Customer customer, Car car, int lengthOfRent, int totalPrice, Instant rentedOn, Instant rentedTo) {
        this.customer = customer;
        this.car = car;
        this.lengthOfRent = lengthOfRent;
        this.totalPrice = totalPrice;
        this.rentedOn = rentedOn;
        this.rentedTo = rentedTo;
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

    public Instant getRentedOn() {
        return rentedOn;
    }

    public void setRentedOn(Instant rentedOn) {
        this.rentedOn = rentedOn;
    }

    public Instant getRentedTo() {
        return rentedTo;
    }

    public void setRentedTo(Instant rentedTo) {
        this.rentedTo = rentedTo;
    }

    @Override
    public String toString() {
        return "RentalEvent{" +
                "customer=" + customer +
                ", car=" + car +
                ", lengthOfRent=" + lengthOfRent +
                ", totalPrice=" + totalPrice +
                ", rentedOn=" + rentedOn +
                ", rentedTo=" + rentedTo +
                '}';
    }
}
