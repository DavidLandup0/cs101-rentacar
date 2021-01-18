package com.metropolitan.rentacar.repository;

import com.metropolitan.rentacar.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Find one by email, ignore case, returns Optional<User>.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findOneByEmailIgnoreCase(String email);
}