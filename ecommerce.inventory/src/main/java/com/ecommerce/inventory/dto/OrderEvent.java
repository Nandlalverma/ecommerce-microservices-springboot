package com.ecommerce.inventory.dto;

import java.util.List;

public class OrderEvent {

    private Long orderId;
    private Long userId;
    private String status;
    private List<OrderItemEvent> items;

    public OrderEvent(Long orderId, Long userId, List<OrderItemEvent> items) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemEvent> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEvent> items) {
        this.items = items;
    }
    public OrderEvent(){

    }
}
