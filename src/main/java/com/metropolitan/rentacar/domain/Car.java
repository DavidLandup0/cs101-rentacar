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

    @Field("price")
    private int price;

    @Field("car_color")
    private CAR_COLOR car_color;

    @Field("available")
    private boolean available;

    public Car() {}

    public Car(String id, String model, int age, CAR_COLOR car_color, boolean available, int price) {
        this.id = id;
        this.model = model;
        this.age = age;
        this.car_color = car_color;
        this.available = available;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
