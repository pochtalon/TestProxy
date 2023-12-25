package com.example.testforproxy.service

import com.example.testforproxy.dto.user.UserInfoDto
import com.example.testforproxy.dto.user.UserUpdateRequestDto
import com.example.testforproxy.dto.user.auth.UserRegistrationRequestDto
import com.example.testforproxy.dto.user.auth.UserResponseDto
import com.example.testforproxy.model.User

interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request)

    UserInfoDto getUserInfo(User user)

    UserInfoDto updateUserInfo(User user, UserUpdateRequestDto userUpdateRequestDto)
}