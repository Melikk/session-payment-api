package com.example.sessionpaymentapi.repository;

import com.example.sessionpaymentapi.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByKey(UUID key);
}
