package com.example.sessionpaymentapi.repository;

import com.example.sessionpaymentapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
