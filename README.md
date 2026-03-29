# 🛒 E-Commerce Backend (Spring Boot Microservices)

## 📌 Overview

This project is a **production-ready E-Commerce Backend System** built using **Spring Boot Microservices Architecture**.
It includes authentication, product management, cart, order processing, inventory handling, and event-driven communication using Kafka.

---

## 🚀 Features

### 🔐 Authentication & Security

* JWT-based Authentication (Access + Refresh Token)
* Role-Based Authorization (USER / ADMIN)
* Secure APIs using Spring Security
* Forgot Password & Reset Password via Email

### 🛍️ Core Modules

* **User Service** – Registration, Login, Roles
* **Product Service** – Add, Update, Search, Filter Products
* **Cart Service** – Add/Remove Items, Update Quantity
* **Order Service** – Place Orders, Order History
* **Inventory Service** – Stock Management
* **Payment Service** – Payment Flow Integration (Extensible)

### 📡 Event-Driven Architecture

* Kafka integration for:

  * Order Events
  * Inventory Updates
  * Notifications

### 📧 Notification System

* Email Service for:

  * Order Confirmation
  * Password Reset

---

## 🛠 Tech Stack

* **Backend:** Java 8, Spring Boot
* **Security:** Spring Security, JWT
* **Database:** MySQL
* **Messaging:** Apache Kafka
* **Build Tool:** Maven
* **API Testing:** Postman
* **Documentation:** Swagger (OpenAPI)

---

## 🏗️ Architecture

Microservices-based architecture with:

* API Gateway (optional)
* Service-to-service communication
* Event-driven messaging using Kafka

---

## 📦 API Endpoints (Sample)

### 🔐 Auth APIs

* `POST /auth/register`
* `POST /auth/login`
* `POST /auth/refresh`
* `POST /auth/forgot-password`
* `POST /auth/reset-password`

### 🛒 Cart APIs

* `GET /cart`
* `POST /cart/add`
* `DELETE /cart/remove`

### 📦 Order APIs

* `POST /order/place`
* `GET /order/history`

---

## ▶️ Run Locally

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/ecommerce-microservices-springboot.git
cd ecommerce-microservices-springboot
```

### 2️⃣ Configure Database

Update `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ecommerce
    username: root
    password: your_password
```

### 3️⃣ Run Application

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🔐 Security Highlights

* Stateless authentication using JWT
* Refresh token mechanism
* Password encryption using BCrypt
* Secure password reset flow

---

## 📸 Future Enhancements

* Docker & Kubernetes deployment
* Payment Gateway Integration (Razorpay/Stripe)
* Redis caching
* API Gateway (Spring Cloud Gateway)
* Circuit Breaker (Resilience4j)

---

## 👨‍💻 Author

Nand Lal Verma

---

## ⭐ If you like this project, give it a star!
