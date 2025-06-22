# 🛒 OmniOrderMS

**OmniOrderMS** is a scalable, production-style **Order Management System** built using **Spring Boot Microservices**, designed to simulate real-world e-commerce backend architecture. It demonstrates secure, distributed service interaction using modern tech like **Keycloak, Kafka, Redis**, and **Docker**.

---

## 🚀 Features

- ✅ Spring Boot Microservices Architecture
- ✅ JWT-based Authentication with Keycloak
- ✅ Centralized API Gateway with Routing
- ✅ Service Discovery via Eureka
- ✅ Asynchronous Messaging with Apache Kafka
- ✅ Redis Caching for Performance Optimization
- ✅ MySQL & MongoDB for Persistent Storage
- ✅ Dockerized Setup for All Components

---

## 🧰 Tech Stack

| Category            | Technology                           |
|---------------------|--------------------------------------|
| Microservices        | Spring Boot                          |
| API Gateway          | Spring Cloud Gateway                 |
| Service Discovery    | Eureka                               |
| Authentication       | Keycloak + JWT                       |
| Messaging            | Apache Kafka                         |
| Caching              | Redis                                |
| Databases            | MySQL (Order & Inventory), MongoDB (Product) |
| Containerization     | Docker + Docker Compose              |

---

## 🧱 Modules

| Module            | Port  | Description                          |
|-------------------|-------|--------------------------------------|
| Eureka Server     | 8761  | Service registry                     |
| API Gateway       | 8080  | Central routing and authentication   |
| Order Service     | 8081  | Manages orders                       |
| Product Service   | 8082  | Handles product catalog (MongoDB)    |
| Inventory Service | 8083  | Tracks inventory and availability    |

---

## 🔐 Authentication

- Users authenticate through **Keycloak**
- Keycloak issues a **JWT token**
- The token is validated by the **API Gateway** for every request
- Role-based access control is enforced via Spring Security

---

## 🐳 Running with Docker

```bash
# Start databases and Keycloak
docker-compose up -d

# Run microservices (in separate terminals or as background processes)
cd eureka-server && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
cd order-service && mvn spring-boot:run
cd product-service && mvn spring-boot:run
cd inventory-service && mvn spring-boot:run

Sample API Endpoints
All routes go through http://localhost:8080

POST /api/order - Place an order

GET /api/product - Get all products

GET /api/inventory/{skuCode} - Check inventory

📌 Requires a valid JWT token in the Authorization header:
Authorization: Bearer <your-token>

Architecture Diagram
                        +--------------------+
                        |    Keycloak Auth   |
                        +--------------------+
                                 |
                                 v
+--------------------+     +--------------------+     +----------------------+
|   API Gateway      | <-> | Eureka Discovery   | <-> | All Microservices    |
+--------------------+     +--------------------+     +----------------------+
       |                     (Service Registry)        (Service Discovery)
       |
       v
+------------------+         +------------------+         +------------------+
| Product Service  | <-----> | Inventory Service| <-----> | Order Service    |
| (MySQL)          |         | (MySQL + Redis)  |         | (MySQL + Kafka)  |
+------------------+         +------------------+         +------------------+

                  ↕ Kafka Events (Order Created, Inventory Updated)

                           +------------------+
                           | Kafka Broker     |
                           +------------------+

                           +------------------+
                           | Redis Cache      |
                           +------------------+

                        [All managed via Docker Compose]

