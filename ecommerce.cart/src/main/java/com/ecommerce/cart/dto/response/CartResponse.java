package com.ecommerce.cart.dto.response;

import com.ecommerce.cart.entity.CartItem;

import java.math.BigDecimal;
import java.util.List;

public class CartResponse {

    private List<CartItem> items;
    private BigDecimal totalAmount;
    public CartResponse(){

    }

    public CartResponse(List<CartItem> items, BigDecimal totalAmount) {
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
