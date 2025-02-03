package com.nas.robospring.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// Helper class to represent the login request

@Getter
@Setter
public class SignUpDto {
    private String name;
    private String username;
    private String password;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
// Getters and Setters
}