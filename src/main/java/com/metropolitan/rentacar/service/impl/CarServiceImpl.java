package com.metropolitan.rentacar.service.impl;

import com.metropolitan.rentacar.domain.Car;
import com.metropolitan.rentacar.repository.CarRepository;
import com.metropolitan.rentacar.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) {
        log.debug("Request to save car : {}", car);
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        log.debug("Request to find all cars");
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findOne(String id) {
        log.debug("Request to find car with id : {}", id);
        return carRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete car with id : {}", id);
        carRepository.deleteById(id);
    }
}
