package com.example.visionarycrofting.config.security.util;

public class JwtUtil {
    public static final String SECRET = "myKey1234";
    public static final String AUTH_HEADER = "Authorization";
    public static final long EXPIRED_JETON = 86400000; // one day
    public static final long EXPIRED_JETON_REFRESH = 30*86400000;// 1 month
    public static final String BEARER = "Bearer ";
    public static final String REFRESH_JETON = "/api/v1/jeton/refresh";
    public static final String AUTHORITIES = "authorities";
}
