package com.yelnar.ecommerce.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String msg) {
        super(msg);
    }
}
