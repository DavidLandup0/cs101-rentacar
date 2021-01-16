package com.metropolitan.rentacar.config;

import com.metropolitan.rentacar.domain.User;
import com.metropolitan.rentacar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
/**
 * Ova klasa postoji samo kako bi se preskocio proces pravljenja korisnika
 * od strane fakulteta, s obzirom da postoji jedan "superadmin"
 * Pokretanjem metode pod rutom "/setup" ce napraviti korisnika, enkodirati password
 * i dodati ga u bazu podataka
 */
public class HardcodeSetup {

    @Autowired
    private UserService userService;

    @GetMapping("/setup")
    public ResponseEntity setup() {
        User user = new User();
        user.setEmail("david@metropolitan.ac.rs");
        user.setPassword("12345678");
        User persistedUser = userService.registerUser(user, user.getPassword());

        return ResponseEntity.ok().body(persistedUser);
    }
}
