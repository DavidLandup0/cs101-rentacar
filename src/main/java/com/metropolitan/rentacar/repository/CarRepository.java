package com.metropolitan.rentacar.repository;

import com.metropolitan.rentacar.domain.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Car repository.
 */
@Repository
public interface CarRepository extends MongoRepository<Car, String> {

    /**
     * Find cars if they're available.
     *
     * @return the list
     */
    List<Car> findByAvailableTrue();

    /**
     * Find car if they're not available.
     *
     * @return the list
     */
    List<Car> findByAvailableFalse();
}
