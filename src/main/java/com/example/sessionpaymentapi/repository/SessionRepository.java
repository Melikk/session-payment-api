package com.example.sessionpaymentapi.repository;

import com.example.sessionpaymentapi.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query(value = "select s from Session s where s.key = :key and s.deactivatedAt is null  and  s.expiredAt > :now")
    Optional<Session> findActiveByKey(@Param("key") UUID key, @Param("now") LocalDateTime now);
}
