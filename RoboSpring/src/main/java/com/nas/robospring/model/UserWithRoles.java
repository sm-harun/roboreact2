package com.nas.robospring.model;

import java.util.List;

public class UserWithRoles {
    private User user;
    private List<Role> roles; // List of roles that the user has

    public UserWithRoles(User user, List<Role> roles) {
        this.user = user;
        this.roles = roles;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
