package com.example.testforproxy.controller

import com.example.testforproxy.dto.post.PostDto
import com.example.testforproxy.dto.user.UserInfoDto
import com.example.testforproxy.dto.user.UserUpdateRequestDto
import com.example.testforproxy.model.User
import com.example.testforproxy.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/me")
    @Operation(summary = "Delete user", description = "Delete current user")
    void deleteUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal()
        userService.deleteUser(user)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/me/feed")
    @Operation(summary = "Get feed", description = "Get feed for current user")
    List<PostDto> getFeed(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal()
        userService.getFeed(user, pageable)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/connect")
    @Operation(summary = "Connect to friend", description = "Connect to friend by friend id")
    void connectToUser(Authentication authentication,
                               @RequestParam String friendId) {
        User user = (User) authentication.getPrincipal()
        userService.connect(user, friendId)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/connect")
    @Operation(summary = "Disconnect from friend", description = "Disconnect from friend by friend id")
    void disconnectToUser(Authentication authentication,
                       @RequestParam String friendId) {
        User user = (User) authentication.getPrincipal()
        userService.disconnect(user, friendId)
    }
}
