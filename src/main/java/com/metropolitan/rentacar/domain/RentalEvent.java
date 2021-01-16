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
}
