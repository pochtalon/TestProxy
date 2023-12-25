package com.example.testforproxy.dto.user

import jakarta.validation.constraints.Size
import lombok.Data

@Data
class UserUpdateRequestDto {
    @Size(min = 8, max = 50)
    String email
    @Size(min = 8, max = 100)
    String password
    @Size(min = 8, max = 100)
    String passwordRepeat
    @Size(max = 50)
    String firstName
    @Size(max = 50)
    String lastName
}
