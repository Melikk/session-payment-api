package com.example.sessionpaymentapi.service;

import com.example.sessionpaymentapi.config.SecurityProperties;
import com.example.sessionpaymentapi.service.interfaces.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtServiceImpl implements JwtService {

    SecretKey accessKey;
    SecretKey refreshKey;
    Duration accessTokenDuration;
    Duration refreshTokenDuration;

    public JwtServiceImpl(SecurityProperties properties) {
        this.accessKey = Keys.hmacShaKeyFor(properties.getAccess().getSecret().getBytes());
        this.refreshKey = Keys.hmacShaKeyFor(properties.getRefresh().getSecret().getBytes());
        this.accessTokenDuration = properties.getAccess().getDuration();
        this.refreshTokenDuration = properties.getRefresh().getDuration();
    }

    @Override
    public String generateAccessToken(String sessionKey) {
        return this.generateJwtToken(accessKey, accessTokenDuration, sessionKey);
    }

    @Override
    public String generateRefreshToken(String sessionKey) {
        return this.generateJwtToken(refreshKey, refreshTokenDuration, sessionKey);
    }

    @Override
    public String extractSessionKeyFromAccessToken(String token) {
        return extractSessionKey(token, accessKey);
    }

    @Override
    public String extractSessionKeyFromRefreshToken(String token) {
        return extractSessionKey(token, refreshKey);
    }

    private String extractSessionKey(String token, SecretKey secretKey) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    private String generateJwtToken(SecretKey secretKey, Duration lifetime, String sessionKey) {
        return Jwts.builder()
                .subject(sessionKey)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + lifetime.toMillis()))
                .signWith(secretKey)
                .compact();
    }
}
