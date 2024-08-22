package com.example.sessionpaymentapi.service.interfaces;

import java.util.UUID;

public interface JwtService {
    String generateAccessToken(String sessionKey);

    String generateRefreshToken(String sessionKey);

    String extractSessionKeyFromAccessToken(String token);

    String extractSessionKeyFromRefreshToken(String token);
}
