package com.foo.compass.problem1.exceptions;

/**
 * Created by Luke Tong on 29-Mar-16.
 */
public class InvalidLengthOfInputPhoneNumberException extends Exception {

    public InvalidLengthOfInputPhoneNumberException() {

    }

    public InvalidLengthOfInputPhoneNumberException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
