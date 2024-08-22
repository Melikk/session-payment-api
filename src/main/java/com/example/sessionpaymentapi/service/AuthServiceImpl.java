package com.example.sessionpaymentapi.service;

import com.example.sessionpaymentapi.config.SecurityProperties;
import com.example.sessionpaymentapi.dto.AuthRequest;
import com.example.sessionpaymentapi.dto.AuthResponse;
import com.example.sessionpaymentapi.entity.Session;
import com.example.sessionpaymentapi.entity.User;
import com.example.sessionpaymentapi.repository.UserRepository;
import com.example.sessionpaymentapi.service.interfaces.AuthService;
import com.example.sessionpaymentapi.service.interfaces.JwtService;
import com.example.sessionpaymentapi.service.interfaces.SessionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthServiceImpl implements AuthService {

    SecurityProperties securityProperties;
    JwtService jwtService;
    SessionService sessionService;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsernameAndDeactivatedAtIsNull(request.getUsername())
                .orElseThrow(() -> new RuntimeException("BAD_CREDENTIALS"));
        //TODO сделать исключения


        if (user.getUnlockAt() != null) {

            if (user.getUnlockAt().isAfter(LocalDateTime.now())) {
                //TODO сделать исключения
                throw new RuntimeException("locked");
            }

            user.setAttemptedLogins(0);
            user.setUnlockAt(null);
            userRepository.save(user);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            user.setAttemptedLogins(user.getAttemptedLogins() + 1);

            if (user.getAttemptedLogins() >= securityProperties.getAttemptedLogins()) {
                user.setUnlockAt(LocalDateTime.now().plusMinutes(securityProperties.getLockTime()));
            }

            userRepository.save(user);

            //TODO сделать исключения
            throw new RuntimeException("BAD_CREDENTIALS");
        }

        user.setAttemptedLogins(0);
        user.setUnlockAt(null);
        userRepository.save(user);

        Session session = sessionService.create(user);

        AuthResponse response = AuthResponse.builder()
                .accessToken(jwtService.generateAccessToken(session.getKey().toString()))
                .refreshToken(jwtService.generateRefreshToken(session.getKey().toString()))
                .build();

        log.info("User sign in successfully USER:{} SID:{}", request.getUsername(), session.getId());
        return response;
    }

    @Override
    public void logout(Session session) {
        sessionService.deactivate(session);
    }

    @Override
    public AuthResponse refresh(String refreshToken) {
        try {
            String sessionKey = jwtService.extractSessionKeyFromRefreshToken(refreshToken);
            Session session = sessionService.getBySessionKey(sessionKey);
            session = sessionService.refresh(session);

            AuthResponse response = AuthResponse.builder()
                    .accessToken(jwtService.generateAccessToken(session.getKey().toString()))
                    .refreshToken(jwtService.generateRefreshToken(session.getKey().toString()))
                    .build();

            log.info("User refresh successfully USER:{} SID:{}", session.getUser().getUsername(), session.getId());
            return response;

        } catch (Exception e) {
            //TODO сделать исключения
            throw new RuntimeException("BAD_CREDENTIALS");
        }
    }

}
