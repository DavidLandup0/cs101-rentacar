package com.metropolitan.rentacar.service.impl;

import com.metropolitan.rentacar.domain.Customer;
import com.metropolitan.rentacar.repository.CustomerRepository;
import com.metropolitan.rentacar.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Customer service.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    /**
     * Instantiates a new Customer service.
     *
     * @param customerRepository the customer repository
     */
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        log.debug("Request to save customer : {}", customer);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        log.debug("Request to save customers");
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findOne(String id) {
        log.debug("Request to find customer with id : {}", id);
        return customerRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete customer : {}", id);
        customerRepository.deleteById(id);
    }
}
