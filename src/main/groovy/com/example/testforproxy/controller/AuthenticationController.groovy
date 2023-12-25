package com.example.testforproxy.controller

import com.example.testforproxy.dto.user.auth.UserLoginRequestDto
import com.example.testforproxy.dto.user.auth.UserLoginResponseDto
import com.example.testforproxy.dto.user.auth.UserRegistrationRequestDto
import com.example.testforproxy.dto.user.auth.UserResponseDto
import com.example.testforproxy.exception.RegistrationException
import com.example.testforproxy.security.AuthenticationService
import com.example.testforproxy.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = 'Authentication controller', description = 'Endpoints for registering and logging users')
@RestController
@RequestMapping('/api/auth')
class AuthenticationController {
    @Autowired final UserService userService
    @Autowired final AuthenticationService authenticationService

    @PostMapping('/register')
    @Operation(summary = 'register user')
    UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request) throws RegistrationException {
        userService.register(request)
    }

    @PostMapping('/login')
    @Operation(summary = 'login user')
    UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        authenticationService.authenticate(request)
    }

    @GetMapping('/logout')
    @Operation(summary = 'logout user')
    void logout() {
        println(SecurityContextHolder.context.authentication)
        SecurityContextHolder.clearContext()
        println(SecurityContextHolder.context.authentication)
//        authenticationService.logout()
    }
}