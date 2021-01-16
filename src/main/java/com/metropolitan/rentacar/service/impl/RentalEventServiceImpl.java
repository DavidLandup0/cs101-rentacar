package com.metropolitan.rentacar.service.impl;

import com.metropolitan.rentacar.domain.RentalEvent;
import com.metropolitan.rentacar.repository.RentalEventRepository;
import com.metropolitan.rentacar.service.RentalEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalEventServiceImpl implements RentalEventService {

    private final Logger log = LoggerFactory.getLogger(RentalEventServiceImpl.class);
    private RentalEventRepository rentalEventRepository;

    public RentalEventServiceImpl(RentalEventRepository rentalEventRepository) {
        this.rentalEventRepository = rentalEventRepository;
    }

    @Override
    public RentalEvent save(RentalEvent rentalEvent) {
        log.debug("Request to save rental event : {}", rentalEvent);
        return rentalEventRepository.save(rentalEvent);
    }

    @Override
    public List<RentalEvent> findAll() {
        log.debug("Request to find all rental events");
        return rentalEventRepository.findAll();
    }

    @Override
    public Optional<RentalEvent> findOne(String id) {
        log.debug("Request to find rental event with id : {}", id);
        return rentalEventRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete rental event with id : {}", id);
        rentalEventRepository.deleteById(id);
    }
}
