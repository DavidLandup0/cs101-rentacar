package com.metropolitan.rentacar.service;

import com.metropolitan.rentacar.domain.Car;

import java.util.List;
import java.util.Optional;

/**
 * The interface Car service.
 */
public interface CarService {

    /**
     * Save car.
     *
     * @param car the car
     * @return the car
     */
    Car save(Car car);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Car> findAll();

    /**
     * Find all available list.
     *
     * @return the list
     */
    List<Car> findByAvailableTrue();

    /**
     * Find by available false list.
     *
     * @return the list
     */
    List<Car> findByAvailableFalse();

    /**
     * Find one optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Car> findOne(String id);


    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);
}
