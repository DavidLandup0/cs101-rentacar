package com.metropolitan.rentacar.service;

import com.metropolitan.rentacar.domain.RentalEvent;

import java.util.List;
import java.util.Optional;

public interface RentalEventService {
    RentalEvent save(RentalEvent rentalEvent);

    List<RentalEvent> findAll();

    Optional<RentalEvent> findOne(String id);

    void delete(String id);
}
