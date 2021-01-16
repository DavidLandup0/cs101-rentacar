package com.metropolitan.rentacar.repository;

import com.metropolitan.rentacar.domain.RentalEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalEventRepository extends MongoRepository<RentalEvent, String> {
    List<RentalEvent> findByActiveTrue();
    List<RentalEvent> findByReturnDateBetween(LocalDate startDate, LocalDate endDate);
}
