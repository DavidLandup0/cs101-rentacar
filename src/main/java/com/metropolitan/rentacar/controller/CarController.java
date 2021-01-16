package com.metropolitan.rentacar.controller;

import com.metropolitan.rentacar.domain.Car;
import com.metropolitan.rentacar.domain.Customer;
import com.metropolitan.rentacar.domain.RentalEvent;
import com.metropolitan.rentacar.domain.User;
import com.metropolitan.rentacar.service.CarService;
import com.metropolitan.rentacar.service.CustomerService;
import com.metropolitan.rentacar.service.RentalEventService;
import com.metropolitan.rentacar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars/")
public class CarController {

    private final Logger log = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private RentalEventService rentalEventService;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/inventory")
    public String inventory(Model model) {
        List<Car> availableCarList = carService.findByAvailableTrue();
        List<RentalEvent> activeRentals = rentalEventService.findAll();
        model.addAttribute("availableCarList", availableCarList);
        model.addAttribute("activeRentals", activeRentals);
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

    @GetMapping("/addCar")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "addCar";
    }

    @PostMapping("/addCar")
    public String addListingPost(@ModelAttribute Car car) {
        car.setAvailable(true);
        carService.save(car);
        return "redirect:/cars/viewCar/" + car.getId();
    }

    @GetMapping("/rentCar/{id}")
    public String rentCar(@PathVariable("id") String id, Model model) {
        Optional<Car> car = carService.findOne(id);
        if (!car.isPresent()) {
            return "404";
        }

        model.addAttribute("car", car.get());
        model.addAttribute("rentalEvent", new RentalEvent());
        return "rentCar";
    }

    @PostMapping("/rentCar/{id}")
    public String rentCarPost(@PathVariable("id") String id, @ModelAttribute RentalEvent rentalEvent) {
        Optional<Car> car = carService.findOne(id);
        if (!car.isPresent()) {
            return "404";
        }
        car.get().setAvailable(false);
        carService.save(car.get());

        rentalEvent.setCar(car.get());
        rentalEvent.setRentedTo(rentalEvent.getRentedOn().plus(rentalEvent.getLengthOfRent(), ChronoUnit.DAYS));
        rentalEvent.setTotalPrice(rentalEvent.getLengthOfRent()*rentalEvent.getCar().getPrice());
        customerService.save(rentalEvent.getCustomer());
        rentalEventService.save(rentalEvent);

        return "redirect:/cars/inventory";
    }

    @GetMapping("/editCar/{id}")
    public String editCar(@PathVariable("id") String id, Model model) {
        Optional<Car> car = carService.findOne(id);
        if(!car.isPresent()) {
            return "404";
        }
        model.addAttribute("car", car.get());
        return "editCar";
    }

    @PostMapping("/editCar/{id}")
    public String editCarPost(@PathVariable("id") String id, @ModelAttribute Car newCar) {
        Optional<Car> carOptional = carService.findOne(id);
        if(!carOptional.isPresent()) {
            return "404";
        }
        Car car = carOptional.get();
        newCar.setId(car.getId());
        carService.save(newCar);

        return "redirect:/cars/viewCar/" + id;
    }

    @GetMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable("id") String id) {
        Optional<Car> car = carService.findOne(id);
        if(!car.isPresent()) {
            return "404";
        }
        carService.delete(id);
        return "redirect:/cars/inventory";
    }


}
