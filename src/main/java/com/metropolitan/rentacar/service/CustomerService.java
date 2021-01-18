package com.metropolitan.rentacar.service;

import com.metropolitan.rentacar.domain.Customer;

import java.util.List;
import java.util.Optional;

/**
 * The interface Customer service.
 */
public interface CustomerService {
    /**
     * Save customer.
     *
     * @param customer the customer
     * @return the customer
     */
    Customer save(Customer customer);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Customer> findAll();

    /**
     * Find one optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Customer> findOne(String id);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);
}
