package com.example.testforproxy.dto.user.auth

import groovy.transform.CompileStatic
import groovy.transform.ToString
import groovy.transform.builder.Builder
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import lombok.Data

@CompileStatic
@ToString
@Builder
@Data
class UserLoginRequestDto {
    @NotEmpty
    @Size(min = 8, max = 20)
    @Email
    String email

    @NotEmpty
    @Size(min = 8, max = 20)
    String password
}
