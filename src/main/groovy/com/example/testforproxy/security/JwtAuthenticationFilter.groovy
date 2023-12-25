package com.example.testforproxy.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = 'Authorization'
    private static final String BEARER = 'Bearer '
    private static final int BEGIN_INDEX = 7

    @Autowired final JwtUtil jwtUtil
    @Autowired final UserDetailsService userDetailsService

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = getToken(request)

        if (token != null && jwtUtil.isValidToken(token)) {
            String username = jwtUtil.getUserName(token)
            UserDetails userDetails = userDetailsService.loadUserByUsername(username)
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
            )
            SecurityContextHolder.context.authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION)
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(BEGIN_INDEX)
        }
        null
    }
}
