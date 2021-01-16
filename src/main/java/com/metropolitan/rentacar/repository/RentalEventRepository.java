package com.metropolitan.rentacar.repository;

import com.metropolitan.rentacar.domain.RentalEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalEventRepository extends MongoRepository<RentalEvent, String> {
}
