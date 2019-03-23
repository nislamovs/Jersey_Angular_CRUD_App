package com.jerseyexample.app.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
