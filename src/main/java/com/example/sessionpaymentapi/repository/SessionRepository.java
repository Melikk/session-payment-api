package com.example.sessionpaymentapi.repository;

import com.example.sessionpaymentapi.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
