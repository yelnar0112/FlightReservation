package com.yelnar.ecommerce.service;

import com.yelnar.ecommerce.dto.ReservationRequest;
import com.yelnar.ecommerce.model.Reservation;

public interface ReservationService{
     Reservation bookFlight(ReservationRequest reservationRequest);
}
