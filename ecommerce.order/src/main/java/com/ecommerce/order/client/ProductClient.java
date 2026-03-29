package com.ecommerce.order.client;

import com.ecommerce.order.dto.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProductClient {

    @Autowired
    private WebClient webClient;

    public ProductResponse getProduct(Long productId){
       return null;
    }
}
