package com.metropolitan.rentacar.domain;

import com.metropolitan.rentacar.domain.enums.CAR_COLOR;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * The type Car.
 */
@Document(collection = "cars")
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("model")
    private String model;

    @Field("age")
    private int age;

    @Field("price")
    private int price;

    @Field("car_color")
    private CAR_COLOR car_color;

    @Field("available")
    private boolean available;

    /**
     * Instantiates a new Car.
     */
    public Car() {}

    /**
     * Instantiates a new Car.
     *
     * @param id        the id
     * @param model     the model
     * @param age       the age
     * @param car_color the car color
     * @param available the available
     * @param price     the price
     */
    public Car(String id, String model, int age, CAR_COLOR car_color, boolean available, int price) {
        this.id = id;
        this.model = model;
        this.age = age;
        this.car_color = car_color;
        this.available = available;
        this.price = price;
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
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets car color.
     *
     * @return the car color
     */
    public CAR_COLOR getCar_color() {
        return car_color;
    }

    /**
     * Sets car color.
     *
     * @param car_color the car color
     */
    public void setCar_color(CAR_COLOR car_color) {
        this.car_color = car_color;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets available.
     *
     * @param available the available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
