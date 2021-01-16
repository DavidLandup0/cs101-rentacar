package com.metropolitan.rentacar.service;

import com.metropolitan.rentacar.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findOne(String id);

    void delete(String id);
}
