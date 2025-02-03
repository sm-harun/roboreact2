package com.nas.robospring.configuration;

import com.nas.robospring.model.Role;
import com.nas.robospring.model.User;
import com.nas.robospring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user; // Store the user entity

    private final RoleRepository roleRepository;

    public CustomUserDetails(User user, RoleRepository roleRepository) {
        this.user = user;
        this.roleRepository = roleRepository;
    }


    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

 /*   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapRolesToAuthorities(user.getRoles()); // Implement your role mapping
    }*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return loadAuthorities().collectList().block(); // Loads the authorities as a blocking operation (use cautiously)
    }

    private Flux<? extends GrantedAuthority> loadAuthorities() {
        return roleRepository.findRolesByUserId(user.getId()) // Assuming you have a method that returns Flux<Role>
                .map(role -> new SimpleGrantedAuthority(role.getName())); // Map Role to GrantedAuthority
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Logic for account expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Logic for account locking
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Logic for credential expiration
    }

    @Override
    public boolean isEnabled() {
        return true; // Logic for user enabling
    }

  /*  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }*/
}
