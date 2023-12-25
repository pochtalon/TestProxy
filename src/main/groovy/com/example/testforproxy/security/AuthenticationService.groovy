package com.example.testforproxy.security

import com.example.testforproxy.dto.user.auth.UserLoginRequestDto
import com.example.testforproxy.dto.user.auth.UserLoginResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationService {
    @Autowired final JwtUtil jwtUtil
    @Autowired final AuthenticationManager authenticationManager

    UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        def authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.email,
                        requestDto.password
                )
        )
        def token = jwtUtil.generateToken(authentication.name)
        new UserLoginResponseDto(token)
    }
}
