package com.ecommerce.cart.service;

import com.ecommerce.cart.dto.request.AddToCart;
import com.ecommerce.cart.dto.response.CartResponse;
import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.entity.CartItem;
import com.ecommerce.cart.repository.CartItemRepository;
import com.ecommerce.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    @Autowired
    private  RedisTemplate<String,Object> redisTemplate;
    private  String CART_PREFIX = "cart:";

    public void addToCart(Long userId,CartItem item){
         String key = CART_PREFIX + userId;
        List<CartItem> cartItems = getCartItems(userId);
        Optional<CartItem> exist = cartItems.stream().filter(i -> i.getProductId().equals(item.getProductId())).findFirst();
        if(exist.isPresent()){
            exist.get().setQuantity(exist.get().getQuantity().add(item.getQuantity()));
        }else{
            cartItems.add(item);
        }
        redisTemplate.opsForValue().set(key,cartItems);
        redisTemplate.expire(key, Duration.ofHours(2));
    }

    public CartResponse getCart(Long userId){
        List<CartItem> cartItems = getCartItems(userId);

        BigDecimal total = cartItems.stream()
                .map(i -> i.getQuantity().multiply(i.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        CartResponse response = new CartResponse();
        response.setTotalAmount(total);
        return response;
    }

    public void removeItem(Long userId,Long productId){
        List<CartItem> cartItems = getCartItems(userId);
        cartItems.removeIf(i->i.getProductId().equals(productId));
        redisTemplate.opsForValue().set(CART_PREFIX + userId,cartItems);
    }

    public void clearCart(Long userId){
       redisTemplate.delete(CART_PREFIX + userId);
    }

   public List<CartItem> getCartItems(Long userId){

       String key = CART_PREFIX + userId;
       List<CartItem> cart =(List<CartItem>)redisTemplate.opsForValue().get(key);
       return cart != null ? cart : new ArrayList<>();
   }
}
