package com.example.sessionpaymentapi.repository;

import com.example.sessionpaymentapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
