package com.yelnar.ecommerce.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Passenger extends AbstractEntity{
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;



}
