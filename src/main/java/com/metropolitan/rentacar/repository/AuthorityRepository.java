package com.metropolitan.rentacar.repository;

import com.metropolitan.rentacar.domain.Authority;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorityRepository extends MongoRepository<Authority, String> {
}
