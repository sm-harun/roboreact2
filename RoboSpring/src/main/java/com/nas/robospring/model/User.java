package com.nas.robospring.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private Set<Role> roles;   // Set of Role objects

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
/*
Comprehensive Structure: The application structure handles users, products, services, events, courses, resources,
 and now includes payment and notification functionalities.
Reactive Programming: By using Spring WebFlux, the application is reactive and non-blocking, allowing
it to efficiently handle multiple users and requests.
CRUD Functionality: Each entity is equipped with CRUD functionalities to enable full management through the application.
Further Enhancements
WebSocket for Real-Time Notifications: Consider using WebSocket to push real-time notifications to users.
Detailed Analytics: Build endpoints to generate analytics on payment statuses or user engagement based on alerts.
Enhanced Security: Ensure proper handling and encryption of sensitive data (like passwords, payment info).


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
}



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("app_user") // Use @Table for R2DBC
public class User {
    @Id
    private Integer id;
    private String username;
    private String email;
    private String password;*/
/*

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    protected String id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;

    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "DELETED")
    protected Boolean deleted = false;

    @Column(name = "ACTIVE")
    protected Boolean active = true;

//    private Set<UserRole> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
   username
passwordHash
email
role (e.g., student, teacher, admin)*/



/*

import com.nas.restaurantapp.configuration.UserRegisterCommand;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Collection;



import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("app_user")
public class User {

    @Id
    protected String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    protected Boolean deleted = false;

    protected Boolean active = true;

//    private Set<UserRole> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public static User create(final UserRegisterCommand command) {
        final User user = new User();

        user.username = command.getUsername();
        user.firstName = command.getFirstName();
        user.lastName = command.getLastName();
        user.email = command.getEmail();
        user.password = command.getPassword();

        return user;
    }
   */
/* // Method to return authorities
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name())) // Convert enum to GrantedAuthority
                .collect(Collectors.toSet());
    }

    // Method to add a role to a user
    public void addRole(UserRole role) {
        this.roles.add(role);
    }*//*

}
*/
