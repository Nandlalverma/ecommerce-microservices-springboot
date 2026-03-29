package com.ecommerce.cart.repository;

import com.ecommerce.cart.entity.Cart;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUserId(Long userId);
    Long  getUserIdByEmail(String email);

}
