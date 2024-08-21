package com.example.sessionpaymentapi.service.interfaces;

import java.util.UUID;

public interface JwtService {
    String generateAccessToken(UUID sessionKey);

    String generateRefreshToken(UUID sessionKey);

    UUID extractSessionKeyFromAccessToken(String token);

    UUID extractSessionKeyFromRefreshToken(String token);
}
