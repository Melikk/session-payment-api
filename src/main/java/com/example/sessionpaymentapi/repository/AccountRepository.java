package com.example.sessionpaymentapi.repository;

import com.example.sessionpaymentapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
