package com.metropolitan.rentacar.repository;

import com.metropolitan.rentacar.domain.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

    List<Car> findByAvailableTrue();
    List<Car> findByAvailableFalse();
}
