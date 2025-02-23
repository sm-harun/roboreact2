package com.nas.robospring.Exception;
import org.springframework.http.HttpStatus;

public class UserAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public UserAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public UserAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}