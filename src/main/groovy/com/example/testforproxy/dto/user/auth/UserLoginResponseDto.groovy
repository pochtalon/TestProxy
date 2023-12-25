package com.example.testforproxy.dto.user.auth

import groovy.transform.ToString
import groovy.transform.builder.Builder
import lombok.Data

@ToString
@Builder
@Data
class UserLoginResponseDto {
    String token

    UserLoginResponseDto(String token) {
        this.token = token
    }
}