package com.example.testforproxy.dto.user

import lombok.Data

@Data
class UserInfoDto {
    String email
    String firstName
    String lastName

    UserInfoDto(String email, String firstName, String lastName) {
        this.email = email
        this.firstName = firstName
        this.lastName = lastName
    }
}
