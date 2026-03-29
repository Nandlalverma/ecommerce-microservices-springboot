package com.ecommerce.cart.dto.request;

import java.math.BigDecimal;

public class AddToCart {
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal price;

    public AddToCart(){

    }

    public AddToCart(Long productId, BigDecimal quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
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
