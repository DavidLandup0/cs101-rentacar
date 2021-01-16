package com.metropolitan.rentacar.controller;

import com.metropolitan.rentacar.domain.Car;
import com.metropolitan.rentacar.service.CarService;
import com.metropolitan.rentacar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(Model model, Principal principal) {

        List<Car> carList = carService.findAll();


        model.addAttribute("carList", carList);

        return "home";
    }

}
