package com.example.visionarycrofting.config.security;

public class JwtUtil {
    public static final String SECRET = "myKey1234";
    public static final String AUTH_HEADER = "Authorization";
    public static final long EXPIRED_JETON = 262980000; // 1 month 262980000 * 10
    public static final long EXPIRED_JETON_REFRESH = 315360000;// 1 yours 315360000 * 100
    public static final String BEARER = "Bearer ";
    public static final String REFRESH_JETON = "/api/v1/jeton/refresh";
}
