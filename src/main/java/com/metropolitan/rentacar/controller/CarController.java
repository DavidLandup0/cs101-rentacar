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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

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
        List<RentalEvent> activeRentals = rentalEventService.findByActiveTrue();
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
        rentalEvent.setRentedTo(rentalEvent.getRentedOn().plus(rentalEvent.getLengthOfRent(), DAYS));
        rentalEvent.setTotalPrice(rentalEvent.getLengthOfRent()*rentalEvent.getCar().getPrice());
        rentalEvent.setActive(true);
        customerService.save(rentalEvent.getCustomer());
        rentalEventService.save(rentalEvent);

        return "redirect:/cars/inventory";
    }

    @GetMapping("/viewRental/{id}")
    public String viewRental(@PathVariable("id") String id, Model model) {
        Optional<RentalEvent> rentalEventOptional = rentalEventService.findOne(id);
        if(!rentalEventOptional.isPresent()) {
            return "404";
        }

        model.addAttribute("rentalEvent", rentalEventOptional.get());
        model.addAttribute("freeAfter", Instant.now().until(rentalEventOptional.get().getRentedTo(), DAYS));

        return "viewRental";
    }

    @GetMapping("/endRental/{id}")
    public String endRental(@PathVariable("id") String id, Model model) {
        Optional<RentalEvent> rentalEventOptional = rentalEventService.findOne(id);
        if(!rentalEventOptional.isPresent()) {
            return "404";
        }
        RentalEvent rentalEvent = rentalEventOptional.get();
        if(Instant.now().isAfter(rentalEventOptional.get().getRentedTo())) {
            model.addAttribute("extraCharge", DAYS.between(rentalEvent.getRentedTo(), Instant.now())*rentalEvent.getCar().getPrice());
        }
        model.addAttribute("rentalEvent", rentalEventOptional.get());
        return "endRental";
    }

    @PostMapping("/endRental/{id}")
    public String endRentalPost(@PathVariable("id") String id) {
        Optional<RentalEvent> rentalEventOptional = rentalEventService.findOne(id);
        if(!rentalEventOptional.isPresent()) {
            return "404";
        }
        RentalEvent rentalEventOld = rentalEventOptional.get();
        rentalEventOld.getCar().setAvailable(true);
        rentalEventOld.setReturnDate(Instant.now());
        if (rentalEventOld.getReturnDate().isAfter(rentalEventOld.getRentedTo())) {
            rentalEventOld.setCharged(rentalEventOld.getTotalPrice() + (int) Math.abs(DAYS.between(rentalEventOld.getReturnDate(), rentalEventOld.getRentedTo())*rentalEventOld.getCar().getPrice()));
        } else {
            rentalEventOld.setCharged(rentalEventOld.getTotalPrice());
        }
        rentalEventOld.setActive(false);

        carService.save(rentalEventOld.getCar());
        rentalEventService.save(rentalEventOld);

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

    @GetMapping("/report")
    public String report( @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
                          @RequestParam(value = "endDate", required =  false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate,
                          Model model) {
        if(startDate == null) {
            startDate = Instant.now().minus(30, DAYS).atZone(ZoneId.of("Europe/London")).toLocalDate();
        }
        if(endDate == null) {
            endDate = Instant.now().plus(1, DAYS).atZone(ZoneId.of("Europe/London")).toLocalDate();
        }
        List<RentalEvent> rentalEventList = rentalEventService.findByReturnDateBetween(startDate, endDate);

        model.addAttribute("rentalEventList", rentalEventList);
        return "report";
    }


    @GetMapping("/export-csv")
    public void exportCSV(HttpServletResponse response) throws IOException {
        final String filename = "izvestaj.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        List<RentalEvent> rentalEventList = rentalEventService.findAll();
        PrintWriter writer = response.getWriter();
        writer.write("Model, Izdato od, Izdato do, Vraceno, Musterija, Naplaceno \n");
        for (RentalEvent re : rentalEventList) {
            writer.write(re.getCar().getModel() + "," + re.getRentedOn() + "," + re.getRentedTo() + "," + re.getReturnDate() + "," + re.getCustomer().getName() + "," + re.getCharged() + "\n");
        }
    }


}
