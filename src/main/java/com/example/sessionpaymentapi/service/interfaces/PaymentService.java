package com.example.sessionpaymentapi.service.interfaces;

import com.example.sessionpaymentapi.dto.TransactionResponse;
import com.example.sessionpaymentapi.entity.Session;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentService {
    @Transactional(isolation = Isolation.SERIALIZABLE)
    TransactionResponse processPayment(Session session);
}
