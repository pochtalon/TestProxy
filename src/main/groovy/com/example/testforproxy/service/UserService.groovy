package com.example.testforproxy.service

import com.example.testforproxy.dto.post.PostDto
import com.example.testforproxy.dto.user.UserInfoDto
import com.example.testforproxy.dto.user.UserUpdateRequestDto
import com.example.testforproxy.dto.user.auth.UserRegistrationRequestDto
import com.example.testforproxy.dto.user.auth.UserResponseDto
import com.example.testforproxy.model.User
import org.springframework.data.domain.Pageable

interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request)

    UserInfoDto getUserInfo(User user)

    UserInfoDto updateUserInfo(User user, UserUpdateRequestDto userUpdateRequestDto)

    void connect(User user, String friendId)

    void disconnect(User user, String friendId)

    void deleteUser(User user)

    List<PostDto> getFeed(User user, Pageable pageable)
}