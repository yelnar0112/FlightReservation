package com.yelnar.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Reservation extends AbstractEntity{
    private boolean checkedIn;

    private int numberOfBags;

    @OneToOne
    private Passenger passenger;

    @OneToOne
    private Flight flight;

}
