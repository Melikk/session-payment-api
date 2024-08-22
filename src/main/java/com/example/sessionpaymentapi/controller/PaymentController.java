package com.example.sessionpaymentapi.controller;

import com.example.sessionpaymentapi.entity.Session;
import com.example.sessionpaymentapi.service.interfaces.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {

    PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<?> payment(@AuthenticationPrincipal Session session) {
        return ResponseEntity.ok(paymentService.processPayment(session));
    }
}
