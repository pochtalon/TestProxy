package com.example.testforproxy.dto.user.auth

import groovy.transform.CompileStatic
import groovy.transform.ToString
import groovy.transform.builder.Builder
import lombok.Data

@CompileStatic
@ToString
@Builder
@Data
class UserResponseDto {
    String id
    String email

    UserResponseDto(String id, String email) {
        this.id = id
        this.email = email
    }
}
