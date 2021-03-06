package com.metropolitan.rentacar.security;


import com.metropolitan.rentacar.domain.User;
import com.metropolitan.rentacar.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Domain user details service.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    /**
     * Instantiates a new DomainUserDetailsService.
     *
     * @param userRepository the user repository
     */
    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Load a user from the database, given a username
     * @param email
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(final String email) {
        log.debug("Authenticating {}", email);

        return userRepository.findOneByEmailIgnoreCase(email)
                .map(user -> createSpringSecurityUser(email, user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found in the database"));
    }

    /**
     * Create user in the context of Spring Security, by granting a set of SimpleGrantedAuthorities based on the authority list
     * @param lowerCaseUsername
     * @param user
     * @return
     */
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowerCaseUsername, User user) {

        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                grantedAuthorities);
    }
}
