package com.example.sessionpaymentapi.service.interfaces;

import com.example.sessionpaymentapi.entity.Session;
import com.example.sessionpaymentapi.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SessionService {
    Session getBySessionKey(String sessionKey);

    Session create (User user);

    Session refresh(Session session);

    void deactivate(Session session);
}
