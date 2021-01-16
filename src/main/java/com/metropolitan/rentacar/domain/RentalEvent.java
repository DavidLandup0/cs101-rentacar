package com.metropolitan.rentacar.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

public class RentalEvent {

    @Id
    private String id;

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

    @Field("return_date")
    private Instant returnDate;

    @Field("charged")
    private int charged;

    @Field("active")
    private boolean active;

    public RentalEvent(){}

    public RentalEvent(String id, Customer customer, Car car, int lengthOfRent, int totalPrice, Instant rentedOn, Instant rentedTo, Instant returnDate, int charged, boolean active) {
        this.id = id;
        this.customer = customer;
        this.car = car;
        this.lengthOfRent = lengthOfRent;
        this.totalPrice = totalPrice;
        this.rentedOn = rentedOn;
        this.rentedTo = rentedTo;
        this.returnDate = returnDate;
        this.charged = charged;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Instant getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

    public int getCharged() {
        return charged;
    }

    public void setCharged(int charged) {
        this.charged = charged;
    }

    @Override
    public String toString() {
        return "RentalEvent{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", car=" + car +
                ", lengthOfRent=" + lengthOfRent +
                ", totalPrice=" + totalPrice +
                ", rentedOn=" + rentedOn +
                ", rentedTo=" + rentedTo +
                ", returnDate=" + returnDate +
                ", charged=" + charged +
                ", active=" + active +
                '}';
    }
}
