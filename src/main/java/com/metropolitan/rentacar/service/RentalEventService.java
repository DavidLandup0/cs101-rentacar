package com.metropolitan.rentacar.service;

import com.metropolitan.rentacar.domain.RentalEvent;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The interface Rental event service.
 */
public interface RentalEventService {
    /**
     * Save rental event.
     *
     * @param rentalEvent the rental event
     * @return the rental event
     */
    RentalEvent save(RentalEvent rentalEvent);

    /**
     * Find by return date between list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    List<RentalEvent> findByReturnDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<RentalEvent> findAll();

    /**
     * Find by active true list.
     *
     * @return the list
     */
    List<RentalEvent> findByActiveTrue();

    /**
     * Find one optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<RentalEvent> findOne(String id);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);
}
