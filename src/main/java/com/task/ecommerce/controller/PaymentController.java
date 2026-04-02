package com.task.ecommerce.controller;

import com.task.ecommerce.entity.Order;
import com.task.ecommerce.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/pay/{orderId}")
    public ResponseEntity<String> payOrder(@PathVariable Long orderId) {


        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));


        if (order.getStatus().equals("PAID")) {
            throw new RuntimeException("Already paid");
        }

        order.setStatus("PAID");
        orderRepository.save(order);

        return ResponseEntity.ok("Payment successful");
    }
}