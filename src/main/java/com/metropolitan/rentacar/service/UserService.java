package com.metropolitan.rentacar.service;

import com.metropolitan.rentacar.domain.User;
import com.metropolitan.rentacar.repository.AuthorityRepository;
import com.metropolitan.rentacar.repository.UserRepository;
import com.metropolitan.rentacar.security.DomainUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    private final DomainUserDetailsService domainUserDetailsService;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository           the user repository
     * @param passwordEncoder          the password encoder
     * @param authorityRepository      the authority repository
     * @param domainUserDetailsService the domain user details service
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository, DomainUserDetailsService domainUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.domainUserDetailsService = domainUserDetailsService;
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<User> findById(String id) {
        log.debug("Request to find user by id {}", id);
        return userRepository.findById(id);
    }
}
