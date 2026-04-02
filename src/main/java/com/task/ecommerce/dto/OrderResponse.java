package com.task.ecommerce.dto;

public class OrderResponse {

    private Long id;
    private Long userId;
    private double totalPrice;
    private String status;

    public OrderResponse(Long id, Long userId, double totalPrice, String status) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
}