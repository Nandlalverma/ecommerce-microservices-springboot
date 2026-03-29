package com.ecommerce.cart.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cartItems")
public class CartItem {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "ProductId", nullable = false)
    private Long productId;
    @Column(name = "quantity")
    private BigDecimal quantity;
    @Column(name = "price")
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    public CartItem(){

    }

    public CartItem(Long id, Long productId, BigDecimal quantity, BigDecimal price, Cart cart) {
        Id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.cart = cart;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
