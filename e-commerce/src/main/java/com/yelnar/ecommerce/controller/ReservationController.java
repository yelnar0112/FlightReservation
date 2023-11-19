package com.yelnar.ecommerce.controller;

import com.yelnar.ecommerce.dto.ReservationRequest;
import com.yelnar.ecommerce.exceptions.FlightNotFound;
import com.yelnar.ecommerce.model.Flight;
import com.yelnar.ecommerce.model.Reservation;
import com.yelnar.ecommerce.repository.FlightRepository;
import com.yelnar.ecommerce.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j
public class ReservationController {
    private final FlightRepository flightRepository;

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(FlightRepository flightRepository, ReservationService reservationService) {
        this.flightRepository = flightRepository;
        this.reservationService = reservationService;
    }


    @GetMapping("/showCompleteReservation/{id}")
    public String showCompleteReservation(@PathVariable("id") Long flightId, ModelMap map){
        log.info("showCompleteReservation() invoked with the Flight Id:  "+ flightId);
        Optional<Flight> flight=flightRepository.findById(flightId);
        if (flight.isEmpty()) {
            log.info("Flight not found: {}", flight);
            throw new FlightNotFound("flightId"+flightId);
        }

        log.info("Flight found: {}",flight);
        map.addAttribute("flight",flight.get());
        return "flights/completeReservation";
    }

    @PostMapping("/completeReservation")
    public String completeReservation(ReservationRequest reservationRequest,ModelMap map){
        log.info("completeReservation() invoked with the Reservation: "+reservationRequest.toString());
        Reservation reservation=reservationService.bookFlight(reservationRequest);
        map.addAttribute("msg","Reservation created successfully and the reservation id is "+reservation.getId());
        return "flights/reservationConfirmation";
    }



}
