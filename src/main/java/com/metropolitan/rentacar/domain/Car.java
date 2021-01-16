package com.metropolitan.rentacar.domain;

import com.metropolitan.rentacar.domain.enums.CAR_COLOR;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document(collection = "cars")
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("model")
    private String model;

    @Field("age")
    private int age;

    @Field("car_color")
    private CAR_COLOR car_color;

    @Field("available")
    private boolean available;

    @Field("customer")
    @DBRef
    private Customer customer;

    @Field("length_of_rent")
    private int lengthOfRent;

    public Car() {}

    public Car(String id, String model, int age, CAR_COLOR car_color, boolean available, Customer customer, int lengthOfRent) {
        this.id = id;
        this.model = model;
        this.age = age;
        this.car_color = car_color;
        this.available = available;
        this.customer = customer;
        this.lengthOfRent = lengthOfRent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public CAR_COLOR getCar_color() {
        return car_color;
    }

    public void setCar_color(CAR_COLOR car_color) {
        this.car_color = car_color;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getLengthOfRent() {
        return lengthOfRent;
    }

    public void setLengthOfRent(int lengthOfRent) {
        this.lengthOfRent = lengthOfRent;
    }
}
