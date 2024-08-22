package com.example.sessionpaymentapi.service.interfaces;

import com.example.sessionpaymentapi.dto.AuthRequest;
import com.example.sessionpaymentapi.dto.AuthResponse;
import com.example.sessionpaymentapi.entity.Session;

public interface AuthService {
    AuthResponse login(AuthRequest request);

    void logout(Session session);

    AuthResponse refresh(String refreshToken);
}
