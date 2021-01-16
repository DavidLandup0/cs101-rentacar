package com.metropolitan.rentacar.service;

import com.metropolitan.rentacar.domain.RentalEvent;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalEventService {
    RentalEvent save(RentalEvent rentalEvent);
    List<RentalEvent> findByReturnDateBetween(LocalDate startDate, LocalDate endDate);

    List<RentalEvent> findAll();
    List<RentalEvent> findByActiveTrue();

    Optional<RentalEvent> findOne(String id);

    void delete(String id);
}
