package com.yelnar.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationRequest{
    private Long flightId;
    private String passengerFirstName;
    private String passengerMiddleName;
    private String passengerLastName;
    private String passengerEmail;
    private String passengerPhone;
    private String nameOnTheCard;
    private String cardNumber;


    private String expirationDate;
    private String securityCode;


}
