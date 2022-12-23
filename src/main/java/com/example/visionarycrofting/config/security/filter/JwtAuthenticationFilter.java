package com.example.visionarycrofting.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.visionarycrofting.config.security.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("attemptAuthentication");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return this.authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authResult) throws IOException, ServletException {
        if (request.getServletPath().equals(JwtUtil.REFRESH_JETON))
            filterChain.doFilter(request, response);
        else if (request.getServletPath().startsWith("/swagger-ui/"))
            filterChain.doFilter(request, response);
        else {
            System.out.println("successfulAuthentication");
            User user = (User) authResult.getPrincipal();
            // Crypto algo symmetric with une secret
            Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
            // create jeton web token : success token
            List<String> authority = user.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
            String jwtSuccessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRED_JETON * 10))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("authority", authority)
                    .sign(algorithm);
            // create jeton web token : refresh token
            String jwtRefreshToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRED_JETON_REFRESH * 100))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);
            Map<String, String> jeton = new HashMap<>();

            jeton.put("successJeton", jwtSuccessToken);
            jeton.put("refreshJeton", jwtRefreshToken);
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), jeton);
        }
    }
}
