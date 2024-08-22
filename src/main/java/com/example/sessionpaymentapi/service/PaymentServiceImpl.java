package com.example.sessionpaymentapi.service;

import com.example.sessionpaymentapi.dto.TransactionResponse;
import com.example.sessionpaymentapi.entity.Account;
import com.example.sessionpaymentapi.entity.Session;
import com.example.sessionpaymentapi.entity.Transaction;
import com.example.sessionpaymentapi.entity.User;
import com.example.sessionpaymentapi.enums.TransactionType;
import com.example.sessionpaymentapi.exception.InsufficientFundsException;
import com.example.sessionpaymentapi.exception.ResourceNotFoundException;
import com.example.sessionpaymentapi.exception.TransactionFailureException;
import com.example.sessionpaymentapi.repository.AccountRepository;
import com.example.sessionpaymentapi.repository.TransactionRepository;
import com.example.sessionpaymentapi.service.interfaces.PaymentService;
import jakarta.persistence.OptimisticLockException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.PessimisticLockException;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentServiceImpl implements PaymentService {
    AccountRepository accountRepository;
    TransactionRepository transactionRepository;

    private static final BigDecimal WITHDRAWAL_AMOUNT = new BigDecimal("1.10");

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TransactionResponse processPayment(Session session) {
        try {
        User user = session.getUser();
        Account account = accountRepository.findByUser_Id(user.getId())
                .orElseThrow(ResourceNotFoundException::new);

        if (account.getBalance().compareTo(WITHDRAWAL_AMOUNT) < 0) {
            throw new InsufficientFundsException();
        }

        account.setBalance(account.getBalance().subtract(WITHDRAWAL_AMOUNT));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(WITHDRAWAL_AMOUNT)
                .transactionType(TransactionType.WITHDRAWAL)
                .createdAt(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .transactionId(transaction.getId())
                .userId(user.getId())
                .sessionId(session.getId())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .balance(account.getBalance())
                .createdAt(transaction.getCreatedAt())
                .build();
        } catch (PessimisticLockException | OptimisticLockException | CannotAcquireLockException e) {
            throw new TransactionFailureException();
        }
    }

}
