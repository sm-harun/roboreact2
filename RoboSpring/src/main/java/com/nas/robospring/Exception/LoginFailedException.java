package com.nas.robospring.Exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String message) {
        super(message); // Pass the message to the superclass constructor
    }
}