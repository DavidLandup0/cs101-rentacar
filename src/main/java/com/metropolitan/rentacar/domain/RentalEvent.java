package com.metropolitan.rentacar.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

/**
 * The type Rental event.
 */
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

    /**
     * Instantiates a new Rental event.
     */
    public RentalEvent(){}

    /**
     * Instantiates a new Rental event.
     *
     * @param id           the id
     * @param customer     the customer
     * @param car          the car
     * @param lengthOfRent the length of rent
     * @param totalPrice   the total price
     * @param rentedOn     the rented on
     * @param rentedTo     the rented to
     * @param returnDate   the return date
     * @param charged      the charged
     * @param active       the active
     */
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

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets car.
     *
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets car.
     *
     * @param car the car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Gets length of rent.
     *
     * @return the length of rent
     */
    public int getLengthOfRent() {
        return lengthOfRent;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets length of rent.
     *
     * @param lengthOfRent the length of rent
     */
    public void setLengthOfRent(int lengthOfRent) {
        this.lengthOfRent = lengthOfRent;
    }

    /**
     * Gets total price.
     *
     * @return the total price
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets total price.
     *
     * @param totalPrice the total price
     */
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets rented on.
     *
     * @return the rented on
     */
    public Instant getRentedOn() {
        return rentedOn;
    }

    /**
     * Sets rented on.
     *
     * @param rentedOn the rented on
     */
    public void setRentedOn(Instant rentedOn) {
        this.rentedOn = rentedOn;
    }

    /**
     * Gets rented to.
     *
     * @return the rented to
     */
    public Instant getRentedTo() {
        return rentedTo;
    }

    /**
     * Sets rented to.
     *
     * @param rentedTo the rented to
     */
    public void setRentedTo(Instant rentedTo) {
        this.rentedTo = rentedTo;
    }

    /**
     * Gets return date.
     *
     * @return the return date
     */
    public Instant getReturnDate() {
        return returnDate;
    }

    /**
     * Sets return date.
     *
     * @param returnDate the return date
     */
    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Gets charged.
     *
     * @return the charged
     */
    public int getCharged() {
        return charged;
    }

    /**
     * Sets charged.
     *
     * @param charged the charged
     */
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
