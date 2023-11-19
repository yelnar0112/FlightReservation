package com.yelnar.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationUpdateRequest {
    private Long id;
    private boolean checkedIn;
    private int numberOfBags;

}
