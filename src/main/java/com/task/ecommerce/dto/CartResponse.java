package com.task.ecommerce.dto;

public class CartResponse {

    private Long id;
    private Long userId;

    public CartResponse(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
}