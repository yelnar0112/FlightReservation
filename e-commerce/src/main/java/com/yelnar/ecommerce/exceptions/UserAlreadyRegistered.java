package com.yelnar.ecommerce.exceptions;

public class UserAlreadyRegistered extends RuntimeException{
    public UserAlreadyRegistered(String msg) {
        super(msg);
    }
}
