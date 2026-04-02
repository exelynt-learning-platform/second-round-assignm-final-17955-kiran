package com.task.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders") // 'order' is reserved keyword
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double totalPrice;
    private String status; // PENDING, PAID

    // SHIPPING DETAILS
    private String address;
    private String city;
    private String pincode;

    // getters
    public Long getId() { return id; }
    public User getUser() { return user; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }

    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getPincode() { return pincode; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setStatus(String status) { this.status = status; }

    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setPincode(String pincode) { this.pincode = pincode; }
}