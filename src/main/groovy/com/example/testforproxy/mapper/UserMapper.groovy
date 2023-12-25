package com.example.testforproxy.mapper

import com.example.testforproxy.dto.user.UserInfoDto
import com.example.testforproxy.dto.user.auth.UserRegistrationRequestDto
import com.example.testforproxy.dto.user.auth.UserResponseDto
import com.example.testforproxy.model.User

interface UserMapper {
    User toModel(UserRegistrationRequestDto request);

    UserResponseDto toDto(User user)

    UserInfoDto toInfoDto(User user)
}