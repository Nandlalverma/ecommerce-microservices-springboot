package com.ecommerce.order.client;

import com.ecommerce.order.dto.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CartClient {

   @Autowired
    private WebClient webClient;

   public CartResponse getCart(Long userId){
        return null;
   }

   public void clearCart(Long userId){

   }

}
