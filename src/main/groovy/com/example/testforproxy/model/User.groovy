package com.example.testforproxy.model

import groovy.transform.ToString
import groovy.transform.builder.Builder
import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@ToString
@Builder
@Data
@Document(collection = "users")
class User implements UserDetails {
    @Id
    String id
    String email
    String password
    String firstName
    String lastName
    List<Post> posts
    Set<User> friends
    Set<RoleName> roles

    User() {
    }

    enum RoleName {
        ROLE_ADMIN,
        ROLE_USER
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.name()))
                .toList()
    }

    @Override
    String getPassword() {
        return password
    }

    @Override
    String getUsername() {
        return email
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return true
    }

}
