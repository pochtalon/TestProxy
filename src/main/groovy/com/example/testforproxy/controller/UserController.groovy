package com.example.testforproxy.controller

import com.example.testforproxy.dto.user.UserInfoDto
import com.example.testforproxy.dto.user.UserUpdateRequestDto
import com.example.testforproxy.model.User
import com.example.testforproxy.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "User management", description = "Endpoints for managing users")
@RestController
@RequestMapping('/api/users')
class UserController {
    @Autowired final UserService userService

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/me")
    @Operation(summary = "Get user info", description = "Get info for current user")
    UserInfoDto getUserInfo(Authentication authentication) {
        User user = (User) authentication.getPrincipal()
        userService.getUserInfo(user)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/me")
    @Operation(summary = "Update user info", description = "Change info for current user")
    UserInfoDto updateUserInfo(Authentication authentication,
                                      @RequestBody @Valid UserUpdateRequestDto infoRequest) {
        User user = (User) authentication.getPrincipal()
        userService.updateUserInfo(user, infoRequest)
    }
}
