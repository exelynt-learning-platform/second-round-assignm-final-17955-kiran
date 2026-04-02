package com.task.ecommerce.controller;

import com.task.ecommerce.dto.OrderResponse;
import com.task.ecommerce.entity.Order;
import com.task.ecommerce.entity.CartItem;
import com.task.ecommerce.entity.User;
import com.task.ecommerce.dto.CreateOrderRequest;
import com.task.ecommerce.repository.OrderRepository;
import com.task.ecommerce.repository.CartItemRepository;
import com.task.ecommerce.repository.UserRepository;

import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Order
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {

        Long cartId = request.getCartId();

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> items = cartItemRepository.findByCartId(cartId);

        if (items.isEmpty()) {
            throw new RuntimeException("Cart is empty or not found");
        }

        double total = 0;
        for (CartItem item : items) {
            total += item.getQuantity() * item.getProduct().getPrice();
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(total);
        order.setStatus("PENDING");


        order.setAddress(request.getAddress());
        order.setCity(request.getCity());
        order.setPincode(request.getPincode());

        Order saved = orderRepository.save(order);

        OrderResponse response = new OrderResponse(
                saved.getId(),
                saved.getUser().getId(),
                saved.getTotalPrice(),
                saved.getStatus()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }
}