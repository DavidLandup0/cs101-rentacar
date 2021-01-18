package com.metropolitan.rentacar.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * The type Customer.
 */
@Document(collection = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("licenceNo")
    private String licenceNo;


    /**
     * Instantiates a new Customer.
     */
    public Customer() {}

    /**
     * Instantiates a new Customer.
     *
     * @param id        the id
     * @param name      the name
     * @param licenceNo the licence no
     * @param car       the car
     */
    public Customer(String id, String name, String licenceNo, Car car) {
        this.id = id;
        this.name = name;
        this.licenceNo = licenceNo;
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets licence no.
     *
     * @return the licence no
     */
    public String getLicenceNo() {
        return licenceNo;
    }

    /**
     * Sets licence no.
     *
     * @param licenceNo the licence no
     */
    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", licenceNo='" + licenceNo + '\'' +
                '}';
    }
}
