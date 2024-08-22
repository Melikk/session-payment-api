package com.example.sessionpaymentapi.service;

import com.example.sessionpaymentapi.repository.SessionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionServiceImpl implements UserDetailsService {

    SessionRepository sessionRepository;

    //TODO сделать исключения
    @Override
    public UserDetails loadUserByUsername(String sessionKey) throws UsernameNotFoundException {
        return sessionRepository.findByKey(UUID.fromString(sessionKey)).orElseThrow();
    }
}
