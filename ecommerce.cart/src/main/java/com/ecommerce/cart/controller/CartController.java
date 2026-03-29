package com.ecommerce.cart.controller;

import com.ecommerce.cart.dto.response.CartResponse;
import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.entity.CartItem;
import com.ecommerce.cart.repository.CartRepository;
import com.ecommerce.cart.service.CartService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    
    @PostMapping("/add/{userId}")
   public ResponseEntity<String> addToCart(@PathVariable Long userId, @RequestBody CartItem item){
       cartService.addToCart(userId, item);
       return ResponseEntity.ok("Item Added !");
   }

  /* @GetMapping("/{userId}")
   public ResponseEntity<CartResponse> getCart(@PathVariable Long userId){
     return ResponseEntity.ok(cartService.getCart(userId));
   }*/


  @GetMapping("/{userId}")
  public ResponseEntity<CartResponse> getCart(Authentication auth){
      String email = auth.name();
      Long userId = cartRepository.getUserIdByEmail(email);
      return ResponseEntity.ok(cartService.getCart(userId));
  }

   @DeleteMapping("/{userId}/item/{productId}")
   public ResponseEntity<String> removeItem(@PathVariable Long userId,@PathVariable Long productId){
     cartService.removeItem(userId, productId);
     return ResponseEntity.ok("removeItem");
   }

   @DeleteMapping("/{userId}/clear")
   public ResponseEntity<String> clearCart(@PathVariable Long userId){
      cartService.clearCart(userId);
      return ResponseEntity.ok("Cart clear !");
   }

}
