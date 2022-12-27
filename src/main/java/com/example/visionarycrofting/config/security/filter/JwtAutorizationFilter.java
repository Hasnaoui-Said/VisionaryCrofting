package com.example.visionarycrofting.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.visionarycrofting.config.security.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collection;

public class JwtAutorizationFilter extends OncePerRequestFilter {

    @Override
    // any request this method execute et verify sign of user
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("doFilterInternal");
        System.out.println(request.getServletPath());
        System.out.println(JwtUtil.REFRESH_JETON);
        System.out.println("equals res = " + request.getServletPath().equals(JwtUtil.REFRESH_JETON));
        if (request.getServletPath().equals(JwtUtil.REFRESH_JETON)) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationJeton = request.getHeader(JwtUtil.AUTH_HEADER);
            if (authorizationJeton != null && authorizationJeton.startsWith(JwtUtil.BEARER)) {
                try {
                    String jwt = authorizationJeton.substring(JwtUtil.BEARER.length());
                    Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim(JwtUtil.AUTHORITIES).asArray(String.class);
                    Collection<GrantedAuthority> authorities = new ArrayDeque<>();
                    for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role));
                    }
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    // Authenticate user
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    // do it!, next
                    filterChain.doFilter(request, response);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    response.setHeader("error-message", e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            } else {
                // verify if this resources required an authentication
                filterChain.doFilter(request, response);
            }
        }
    }
}
