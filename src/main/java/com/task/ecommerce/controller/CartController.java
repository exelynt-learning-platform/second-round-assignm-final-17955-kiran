package com.task.ecommerce.controller;

import com.task.ecommerce.dto.CartResponse;
import com.task.ecommerce.entity.Cart;
import com.task.ecommerce.entity.CartItem;
import com.task.ecommerce.entity.Product;
import com.task.ecommerce.entity.User;

import com.task.ecommerce.repository.CartItemRepository;
import com.task.ecommerce.repository.CartRepository;
import com.task.ecommerce.repository.ProductRepository;
import com.task.ecommerce.repository.UserRepository;

import com.task.ecommerce.dto.CartItemResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Cart
    @PostMapping("/create")
    public ResponseEntity<CartResponse> createCart() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = new Cart();
        cart.setUser(user);

        Cart saved = cartRepository.save(cart);

        CartResponse response = new CartResponse(
                saved.getId(),
                saved.getUser().getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Add item to cart
    @PostMapping("/add")
    public ResponseEntity<CartItemResponse> addItem(@RequestBody CartItem item) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findById(item.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (!cart.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access to cart");
        }

        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        item.setCart(cart);
        item.setProduct(product);

        CartItem saved = cartItemRepository.save(item);

        // RETURN CLEAN RESPONSE
        CartItemResponse response = new CartItemResponse(
                saved.getId(),
                saved.getProduct().getName(),
                saved.getProduct().getPrice(),
                saved.getQuantity()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get cart items
    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItemResponse>> getCartItems(@PathVariable Long cartId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (!cart.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access");
        }

        List<CartItemResponse> response = cartItemRepository.findAll()
                .stream()
                .filter(item -> item.getCart().getId().equals(cart.getId()))
                .map(item -> new CartItemResponse(
                        item.getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }

    // Remove item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable Long id) {

        if (!cartItemRepository.existsById(id)) {
            throw new RuntimeException("Item not found");
        }

        cartItemRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}