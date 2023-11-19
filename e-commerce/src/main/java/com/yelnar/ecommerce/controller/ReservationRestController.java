package com.yelnar.ecommerce.controller;

import com.yelnar.ecommerce.dto.ReservationUpdateRequest;
import com.yelnar.ecommerce.exceptions.ReservationNotFound;
import com.yelnar.ecommerce.model.Reservation;
import com.yelnar.ecommerce.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class ReservationRestController {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationRestController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
        log.info("Inside findReservation() for id: "+id);
        Optional<Reservation> reservation=reservationRepository.findById(id);
        if(reservation.isEmpty()){
            log.error("No reservation exist with id "+id);
            throw new ReservationNotFound("No reservation exist with id "+id);
        }
        return reservation.get();
    }


    @PostMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest updateRequest){
        Optional<Reservation> reservation=reservationRepository.findById(updateRequest.getId());
        log.info("Inside updateReservation() for "+updateRequest);
        if(reservation.isEmpty()){
            log.error("No reservation exist with id "+updateRequest.getId());
            throw new ReservationNotFound("No reservation exist with id "+updateRequest.getId());
        }
        reservation.get().setNumberOfBags(updateRequest.getNumberOfBags());
        reservation.get().setCheckedIn(updateRequest.isCheckedIn());
        log.info("Saving Reservation");
        return reservationRepository.save(reservation.get());

    }
}
