package com.example.sessionpaymentapi.repository;

import com.example.sessionpaymentapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndDeactivatedAtIsNull(String username);
}
