package com.example.sessionpaymentapi.service;

import com.example.sessionpaymentapi.config.SecurityProperties;
import com.example.sessionpaymentapi.entity.Session;
import com.example.sessionpaymentapi.entity.User;
import com.example.sessionpaymentapi.exception.ResourceNotFoundException;
import com.example.sessionpaymentapi.repository.SessionRepository;
import com.example.sessionpaymentapi.service.interfaces.SessionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionServiceImpl implements SessionService {

    SessionRepository sessionRepository;
    SecurityProperties securityProperties;

    //TODO сделать исключения
    @Override
    public Session getBySessionKey(String sessionKey) throws UsernameNotFoundException {
        return sessionRepository.findActiveByKey(UUID.fromString(sessionKey), LocalDateTime.now())
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Session create(User user) {
        Session session = Session.builder()
                .user(user)
                .expiredAt(LocalDateTime.now().plusMinutes(securityProperties.getRefresh().getLifeTime()))
                .key(UUID.randomUUID())
                .build();
        return sessionRepository.save(session);
    }

    @Override
    public Session refresh(Session session) {
        session.setKey(UUID.randomUUID());
        session.setExpiredAt(LocalDateTime.now().plusMinutes(securityProperties.getRefresh().getLifeTime()));
        return sessionRepository.save(session);
    }

    @Override
    public void deactivate(Session session) {
        session.setKey(null);
        session.setDeactivatedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }
}
