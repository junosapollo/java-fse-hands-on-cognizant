# Microservices with Spring Boot 3

This folder contains working hands-on solutions for the requested exercises:

- User and Order Management using REST APIs, PostgreSQL, JPA, and `WebClient`.
- Product and Inventory Management using Eureka service discovery and Spring Cloud Config Server.
- API Gateway routing to Customer and Billing services with load-balanced routes, request logging, path rewriting, local response caching, rate limiting, and circuit-breaker fallback.
- Payment Service using Resilience4j circuit breaker and fallback logging for a slow/unavailable third-party payment API.

## Modules

| Module | Port | Purpose |
| --- | ---: | --- |
| `discovery-server` | 8761 | Eureka Server |
| `config-server` | 8888 | Spring Cloud Config Server using local native config |
| `user-service` | 8081 | User CRUD |
| `order-service` | 8082 | Order CRUD and user validation through `WebClient` |
| `product-service` | 8083 | Product and stock CRUD |
| `inventory-service` | 8084 | Inventory tracking and product validation through Eureka load balancing |
| `customer-service` | 8085 | Gateway target service |
| `billing-service` | 8086 | Gateway target service |
| `api-gateway` | 8080 | Edge service and API Gateway |
| `payment-service` | 8087 | Resilience4j payment sample |

## Prerequisites

- Java 17
- Maven 3.9+
- Docker, only if you want the provided PostgreSQL setup

Start PostgreSQL:

```bash
docker compose up -d postgres
```

Build all modules:

```bash
mvn clean package
```

## Startup Order

Run each command from this `Microservices` folder in a separate terminal:

```bash
mvn -pl discovery-server spring-boot:run
mvn -pl config-server spring-boot:run
mvn -pl user-service spring-boot:run
mvn -pl order-service spring-boot:run
mvn -pl product-service spring-boot:run
mvn -pl inventory-service spring-boot:run
mvn -pl customer-service spring-boot:run
mvn -pl billing-service spring-boot:run
mvn -pl payment-service spring-boot:run
mvn -pl api-gateway spring-boot:run
```

Open Eureka at `http://localhost:8761` to confirm that the Eureka clients are registered.

## Exercise 1: User and Order Management

Create a user:

```bash
curl -X POST http://localhost:8081/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Asha Rao","email":"asha@example.com"}'
```

Create an order for that user:

```bash
curl -X POST http://localhost:8082/orders \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"itemName":"Laptop","quantity":1}'
```

`order-service` validates the user by calling `user-service` with `WebClient`.

## Exercise 2: Product and Inventory with Eureka and Config Server

`product-service` and `inventory-service` import their port, datasource, JPA, and Eureka settings from `config-server/src/main/resources/config-repo`.

Create a product:

```bash
curl -X POST http://localhost:8083/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Keyboard","sku":"KB-101","stockQuantity":50}'
```

Create inventory for that product:

```bash
curl -X POST http://localhost:8084/inventory \
  -H "Content-Type: application/json" \
  -d '{"productId":1,"availableQuantity":40,"reservedQuantity":10}'
```

`inventory-service` validates products through a load-balanced `WebClient` call to `http://product-service`.

## Exercise 3: API Gateway

Gateway routes:

- `GET http://localhost:8080/customers` routes to `customer-service`.
- `GET http://localhost:8080/billing` routes to `billing-service`.
- `POST http://localhost:8080/payments` routes to `payment-service`.

Implemented gateway features:

- Routing with Spring Cloud Gateway.
- Load balancing with `lb://service-name`, Eureka, and `RandomLoadBalancerConfiguration`.
- Request and response logging with `LoggingGlobalFilter`.
- Path rewriting, for example `/customers/1` to `/api/customers/1`.
- Local response caching using `LocalResponseCache`.
- Rate limiting using `InMemoryRateLimitGatewayFilterFactory`.
- Billing fallback using Gateway `CircuitBreaker` and `ResilienceConfiguration`.

## Exercise 4: Payment Circuit Breaker

`payment-service` calls a third-party payment API at `third-party.payment.url`, defaulting to `http://localhost:9090/payments`.

If that API is slow, down, or failing, Resilience4j opens the `thirdPartyPayment` circuit breaker and calls `fallbackPayment`, which logs the fallback event and returns a `PENDING_MANUAL_REVIEW` response.

Test fallback without starting a third-party service:

```bash
curl -X POST http://localhost:8087/payments \
  -H "Content-Type: application/json" \
  -d '{"orderId":1,"amount":999.00,"currency":"INR"}'
```

Monitor the circuit breaker:

```bash
curl http://localhost:8087/actuator/health
curl http://localhost:8087/actuator/metrics/resilience4j.circuitbreaker.calls
```
