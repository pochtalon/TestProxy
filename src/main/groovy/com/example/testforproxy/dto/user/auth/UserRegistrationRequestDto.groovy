package com.example.testforproxy.dto.user.auth

import groovy.transform.CompileStatic
import groovy.transform.ToString
import groovy.transform.builder.Builder
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import lombok.Data

@CompileStatic
@ToString
@Builder
@Data
class UserRegistrationRequestDto {
    @NotBlank
    @Size(min = 8, max = 50)
    String email
    @NotBlank
    @Size(min = 8, max = 100)
    String password
    @NotBlank
    @Size(min = 8, max = 100)
    String passwordRepeat
    @NotBlank
    @Size(max = 50)
    String firstName
    @NotBlank
    @Size(max = 50)
    String lastName
}
