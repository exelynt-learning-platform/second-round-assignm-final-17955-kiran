package com.task.ecommerce.dto;

import jakarta.validation.constraints.*;

public class CreateOrderRequest {

    @NotNull(message = "Cart ID is required")
    private Long cartId;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Pincode is required")
    private String pincode;

    // getters
    public Long getCartId() {
        return cartId;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }

    // setters
    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}