package com.task.ecommerce;

import com.task.ecommerce.controller.AuthController;
import com.task.ecommerce.dto.LoginRequest;
import com.task.ecommerce.entity.User;
import com.task.ecommerce.repository.UserRepository;
import com.task.ecommerce.util.JwtUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Spy
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    private AuthController authController;

    @Test
    void testLoginSuccess() {

        // create dummy user
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword(passwordEncoder.encode("1234"));

        // database call
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        // token generation
        Mockito.when(jwtUtil.generateToken("test@gmail.com"))
                .thenReturn("token123");

        // request
        LoginRequest request = new LoginRequest();
        request.setEmail("test@gmail.com");
        request.setPassword("1234");

        // call method
        var response = authController.login(request);

        // check result
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("token123", response.getBody());
    }
}