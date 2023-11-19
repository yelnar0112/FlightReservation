package com.yelnar.ecommerce.service;

import com.yelnar.ecommerce.dto.ReservationRequest;
import com.yelnar.ecommerce.exceptions.FlightNotFound;
import com.yelnar.ecommerce.model.Flight;
import com.yelnar.ecommerce.model.Passenger;
import com.yelnar.ecommerce.model.Reservation;
import com.yelnar.ecommerce.repository.FlightRepository;
import com.yelnar.ecommerce.repository.PassengerRepository;
import com.yelnar.ecommerce.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ReservationServiceImpl implements ReservationService{
    private final FlightRepository flightRepository;

    private final PassengerRepository passengerRepository;

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(FlightRepository flightRepository, PassengerRepository passengerRepository, ReservationRepository reservationRepository) {
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public Reservation bookFlight(ReservationRequest reservationRequest) {
        log.info("Inside bookFlight()");
        Long flightId=reservationRequest.getFlightId();
        Optional<Flight> flightOptional=flightRepository.findById(flightId);

        if(flightOptional.isEmpty()){
            throw new FlightNotFound("No flight found with id: "+flightId);
        }
        log.info("flight found with id: {}",flightId);

        Flight flight= flightOptional.get();
        Passenger passenger=new Passenger();
        passenger.setFirstName(reservationRequest.getPassengerFirstName());
        passenger.setLastName(reservationRequest.getPassengerLastName());
        passenger.setMiddleName(reservationRequest.getPassengerMiddleName());
        passenger.setEmail(reservationRequest.getPassengerEmail());
        passenger.setPhone(reservationRequest.getPassengerPhone());

        passengerRepository.save(passenger);
        log.info("Saved new passenger: "+passenger);

        Reservation reservation=new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedIn(false);
        Reservation saveReservation = reservationRepository.save(reservation);
        log.info("Saving new reservation:" +reservation);



        return saveReservation;
    }
}
