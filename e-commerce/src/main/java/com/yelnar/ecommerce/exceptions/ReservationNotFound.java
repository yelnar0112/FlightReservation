package com.yelnar.ecommerce.exceptions;

public class ReservationNotFound extends RuntimeException{
    public ReservationNotFound(String message) {
        super(message);
    }
}
