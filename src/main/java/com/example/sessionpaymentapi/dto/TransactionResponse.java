package com.example.sessionpaymentapi.dto;

import com.example.sessionpaymentapi.enums.TransactionType;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponse {

    Long transactionId;
    Long userId;
    Long sessionId;
    TransactionType transactionType;
    BigDecimal amount;
    BigDecimal balance;
    LocalDateTime createdAt;

}
