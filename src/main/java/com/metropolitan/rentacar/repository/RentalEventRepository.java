package com.metropolitan.rentacar.repository;

import com.metropolitan.rentacar.domain.RentalEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface RentalEventRepository.
 */
@Repository
public interface RentalEventRepository extends MongoRepository<RentalEvent, String> {
    /**
     * Find list of RentalEvent(s) if they're active.
     *
     * @return the list
     */
    List<RentalEvent> findByActiveTrue();

    /**
     * Find by return date, between two dates.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    List<RentalEvent> findByReturnDateBetween(LocalDate startDate, LocalDate endDate);
}
