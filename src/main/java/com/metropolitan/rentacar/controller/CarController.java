package com.metropolitan.rentacar.controller;

import com.metropolitan.rentacar.domain.Car;
import com.metropolitan.rentacar.domain.RentalEvent;
import com.metropolitan.rentacar.service.CarService;
import com.metropolitan.rentacar.service.CustomerService;
import com.metropolitan.rentacar.service.RentalEventService;
import com.metropolitan.rentacar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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


    /**
     * Inventory page.
     * Add list of available cars and active rentals to the model.
     * @param model the model to be passed to the page
     * @return the page
     */
    @GetMapping("/inventory")
    public String inventory(Model model) {
        List<Car> availableCarList = carService.findByAvailableTrue();
        List<RentalEvent> activeRentals = rentalEventService.findByActiveTrue();
        model.addAttribute("availableCarList", availableCarList);
        model.addAttribute("activeRentals", activeRentals);
        return "inventory";
    }

    /**
     * View car page.
     * Check if the car is present, and if it is, populate the viewCar page with info
     * @param id    the id
     * @param model the model to be passed to the page
     * @return the page
     */
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

    /**
     * Add car page.
     * Plain page to collect form data about adding cars
     * @param model the model to be passed to the page
     * @return the page
     */
    @GetMapping("/addCar")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "addCar";
    }

    /**
     * Add listing post page.
     * Post page that accepts a model attribute containing form data and saves the car to the db
     * @param car the car
     * @return the page
     */
    @PostMapping("/addCar")
    public String addListingPost(@ModelAttribute Car car) {
        car.setAvailable(true);
        carService.save(car);
        return "redirect:/cars/viewCar/" + car.getId();
    }

    /**
     * Rent car page.
     *
     * @param id    the id
     * @param model the model
     * @return the page
     */
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

    /**
     * Rent car post page.
     *
     * @param id          the id
     * @param rentalEvent the rental event
     * @return the page
     */
    @PostMapping("/rentCar/{id}")
    public String rentCarPost(@PathVariable("id") String id, @ModelAttribute RentalEvent rentalEvent) {
        rentalEvent.setId(null);
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

    /**
     * View rental page.
     * Checking if the RentalEvent exists, and if so, add the RentalEvent to the model to be passed to the page
     * @param id    the id
     * @param model the model
     * @return the page
     */
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

    /**
     * End rental page.
     * @param id    the id of the RentalEvent
     * @param model the model
     * @return the page
     */
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

    /**
     * End rental post page.
     * End RentalEvent by setting it to inactive, but keepint it in the db
     * Adding extra charge if need be
     * @param id the id of the RentalEvent
     * @return the inventory page
     */
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

    /**
     * Edit car page.
     *
     * @param id    the id
     * @param model the model
     * @return the page
     */
    @GetMapping("/editCar/{id}")
    public String editCar(@PathVariable("id") String id, Model model) {
        Optional<Car> car = carService.findOne(id);
        if(!car.isPresent()) {
            return "404";
        }
        model.addAttribute("car", car.get());
        return "editCar";
    }

    /**
     * Edit car post page.
     * Post page to edit car and save change in the db
     * @param id     the id
     * @param newCar the new car
     * @return the page
     */
    @PostMapping("/editCar/{id}")
    public String editCarPost(@PathVariable("id") String id, @ModelAttribute Car newCar) {
        Optional<Car> carOptional = carService.findOne(id);
        if(!carOptional.isPresent()) {
            return "404";
        }
        Car car = carOptional.get();
        newCar.setId(car.getId());
        carService.save(newCar);

        return "redirect:/cars/inventory";
    }

    /**
     * Delete car page.
     *
     * @param id the id
     * @return the page
     */
    @GetMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable("id") String id) {
        Optional<Car> car = carService.findOne(id);
        if(!car.isPresent()) {
            return "404";
        }
        carService.delete(id);
        return "redirect:/cars/inventory";
    }

    /**
     * Report page.
     * Setup base values for the page, if not supplied via request parameters
     * Return list of RentalEvent(s) between the given dates
     * @param startDate the start date
     * @param endDate   the end date
     * @param model     the model
     * @return the report page
     */
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


    /**
     * Export csv file of the report.
     *
     * @param response the response
     * @throws IOException the io exception
     */
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
