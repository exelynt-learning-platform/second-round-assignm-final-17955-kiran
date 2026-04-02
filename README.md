# second-round-assignm-final-17955-kiran
Final Project Assignment - This repository contains the complete final project code and documentation.

# Description

This project is a backend system for an e-commerce platform developed using Spring Boot. It includes user authentication, product management, cart management, order processing, and payment integration.

# Features
Authentication & Authorization: 
User registration and login
JWT-based authentication using Spring Security
Role-based authorization (User/Admin)

Product Management: 
Create, update, delete, and view products
Product attributes: name, description, price, stock quantity, image URL

Cart Management: 
Add products to cart
Update and remove items from cart
Each user can manage only their own cart

Order Management: 
Create orders from cart
Store total price, shipping details, and payment status
View order details

Payment Integration: 
Implemented simulated payment processing
Handles payment success and failure scenarios
Updates order status accordingly
Designed to be easily integrated with real payment gateways like Stripe or PayPal

# Tech Stack
Java
Spring Boot
Spring Security
JWT
MySQL
JPA / Hibernate

# Database Design
One-to-Many: User → Orders
Many-to-Many: Orders ↔ Products
Cart linked with User and Products

# Error Handling
Proper validation for inputs
Meaningful error messages
HTTP status codes (200, 201, 400, 401, 404)

# Testing
APIs tested using Postman

# Author
Kiran Patkari
