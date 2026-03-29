package com.ecommerce.order.client;

import com.ecommerce.order.dto.response.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentClient {

    public PaymentResponse pay(Long orderId,Double amount){
        return null;
    }
}
