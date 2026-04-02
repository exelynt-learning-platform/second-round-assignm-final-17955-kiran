package com.task.ecommerce.entity;

import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // relation
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // getters
    public Long getId() { return id; }
    public User getUser() { return user; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
}