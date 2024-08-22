package com.example.sessionpaymentapi.repository;

import com.example.sessionpaymentapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUser_Id(Long userId);
}
