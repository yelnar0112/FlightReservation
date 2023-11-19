package com.yelnar.ecommerce.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Flight extends AbstractEntity implements Serializable {
    private String flightNumber;
    private String operatingAirlines;
    private String departureCity;
    private String arrivalCity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfDeparture;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date estimatedDepartureTime;
}
