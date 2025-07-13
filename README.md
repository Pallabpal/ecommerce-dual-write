# # 🛒 E-Commerce Order Service (Outbox Pattern + Kafka + Idempotency + DLQ)

This project demonstrates an **Event-Driven E-Commerce Order Service** using:

- ✅ **Spring Boot** (REST API + Service Layer)
- ✅ **Kafka** for asynchronous event publishing
- ✅ **Outbox Pattern** for atomicity between DB and Kafka
- ✅ **Dead Letter Queue (DLQ)** for failed event recovery
- ✅ **Idempotent Event Handling** to prevent duplicate processing
- ✅ **MySQL** as the relational database

---

## 📌 Architecture Overview

- An order is placed via a REST API.
- The order is saved to the `orders` table.
- An `OutboxEvent` is inserted transactionally in the `outbox` table.
- A scheduled `OutboxPoller` publishes pending events to Kafka.
- Consumers handle Kafka events idempotently.
- Failed events are redirected to a Dead Letter Queue (`order.dlq`).

---

## 🔧 Technologies Used

| Tech         | Role                        |
|--------------|-----------------------------|
| Spring Boot  | Core framework              |
| Spring Data JPA | ORM with MySQL          |
| Apache Kafka | Event broker                |
| MySQL        | Primary database            |
| Jackson      | JSON serialization          |
| HikariCP     | Connection pooling          |
| Lombok *(optional)* | Reduces boilerplate  |

---
## 🚀 Running the Application

### 🧱 Prerequisites

- Java 17+
- MySQL running on `localhost:3306`
- Kafka running on `localhost:9092`
- Kafka topics:
  - `order.events`
  - `order.dlq`


