package com.ecommerce.payment.controller;

import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pay")
public class PaymentController {

    @Autowired
    public PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> pay(@RequestParam Long orderId, @RequestParam Double amount){
        return ResponseEntity.ok(paymentService.processPayment(orderId, amount));
    }
}
