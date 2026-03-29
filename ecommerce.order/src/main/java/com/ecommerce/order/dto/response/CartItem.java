package com.ecommerce.order.dto.response;

import jakarta.persistence.*;

import java.math.BigDecimal;

public class CartItem {

    private Long Id;
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal price;

    public CartItem() {
    }

    public CartItem(Long id, Long productId, BigDecimal quantity, BigDecimal price) {
        Id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
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
}
