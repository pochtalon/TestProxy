package com.example.testforproxy.mapper

import com.example.testforproxy.dto.user.UserInfoDto
import com.example.testforproxy.dto.user.auth.UserRegistrationRequestDto
import com.example.testforproxy.dto.user.auth.UserResponseDto
import com.example.testforproxy.model.User
import org.springframework.stereotype.Component

@Component
class UserMapperImpl implements UserMapper {
    @Override
    User toModel(UserRegistrationRequestDto request) {
        User user = new User()
        user.email = request.email
        user.password = request.password
        user.firstName = request.firstName
        user.lastName = request.lastName
        return user
    }

    @Override
    UserResponseDto toDto(User user) {
        [user.id, user.email] as UserResponseDto
    }

    @Override
    UserInfoDto toInfoDto(User user) {
        [user.email, user.firstName, user.lastName] as UserInfoDto
    }
}
