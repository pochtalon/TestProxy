package com.example.testforproxy.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.function.Function

@Component
class JwtUtil {
    @Value('${jwt.expiration}')
    long expiration
    private final Key secret

    JwtUtil(@Value('${jwt.secret}') String secretString) {
        secret = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8))
    }

    String generateToken(String email) {
        Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secret)
                .compact()
    }

    boolean isValidToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)

            !claimsJws.getBody().getExpiration().before(new Date())
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException('Expired or invalid JWT token')
        }
    }

    String getUserName(String token) {
        getClaimsFromToken(token, Claims.&getSubject)
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        def claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .body
        claimsResolver.apply(claims)
    }
}