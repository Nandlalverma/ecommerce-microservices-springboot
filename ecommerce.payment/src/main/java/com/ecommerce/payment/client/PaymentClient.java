package com.ecommerce.payment.client;

import com.ecommerce.payment.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Configuration
public class PaymentClient {

    @Autowired
    public WebClient webClient;
    public Payment pay(Long id, BigDecimal total){
        return null;
    }


}
