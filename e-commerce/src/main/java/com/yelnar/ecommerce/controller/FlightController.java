package com.yelnar.ecommerce.controller;

import com.yelnar.ecommerce.model.Flight;
import com.yelnar.ecommerce.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class FlightController {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }



    @GetMapping("/findFlights")
    public String findFlight(@RequestParam(value = "source",required = false) String source , @RequestParam(value = "destination",required = false) String destination,
                             @RequestParam(value = "departDate",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date departDate, ModelMap map){

        log.info("Inside findFlight() Form:"+source+"TO:"+destination+"departDate: "+departDate);
        List<Flight> flights = flightRepository.findFlights(source,destination,departDate);
        map.addAttribute("flights",flights);
        log.info("Flights found are: "+flights.toString());
        return "flights/displayFlights";
    }

    @GetMapping("/admin/showAddFlight")
    public String showAddFlightPage(ModelMap map){
        Flight flight=new Flight();
        map.addAttribute("flight",flight);
        return "flights/addFlight";
    }

    @PostMapping("/admin/addFlight")
    public String addFlight(@ModelAttribute("flight") Flight flight,ModelMap map) {
        flightRepository.save(flight);
        map.addAttribute("msg","Flight Added Successfully");
        return "flights/addFlight";
    }
}
