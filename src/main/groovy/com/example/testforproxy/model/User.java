package com.example.testforproxy.model;

import groovy.transform.ToString;
import groovy.transform.builder.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@ToString
@Builder
@Data
@Document(collection = "users")
public
class User implements UserDetails {
    @Id
    String id;
    String email;
    String password;
    String firstName;
    String lastName;
    List<Post> posts;
    Set<User> friends;
    Set<RoleName> roles;

    public User() {
    }

    public enum RoleName {
        ROLE_ADMIN,
        ROLE_USER
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.name()))
                .toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
