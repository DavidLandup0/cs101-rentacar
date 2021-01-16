package com.metropolitan.rentacar.controller;

import com.metropolitan.rentacar.domain.Car;
import com.metropolitan.rentacar.service.CarService;
import com.metropolitan.rentacar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final Logger log = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;


    @GetMapping("/inventory")
    public String inventory(Model model) {
        List<Car> carList = carService.findAll();
        model.addAttribute("carList", carList);
        return "inventory";
    }

    @GetMapping("/viewCar/{id}")
    public String viewCar(@PathVariable("id") String id, Model model) {
        Optional<Car> car = carService.findOne(id);
        if(car.isPresent()) {
            model.addAttribute("car", car.get());
        } else {
            return "error404";
        }
        return "viewCar";
    }
}
