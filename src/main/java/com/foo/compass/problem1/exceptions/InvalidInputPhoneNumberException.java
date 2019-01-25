package com.foo.compass.problem1.exceptions;

/**
 * Created by Luke Tong on 29-Mar-16.
 */
public class InvalidInputPhoneNumberException extends Exception {

    public InvalidInputPhoneNumberException() {

    }

    public InvalidInputPhoneNumberException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
