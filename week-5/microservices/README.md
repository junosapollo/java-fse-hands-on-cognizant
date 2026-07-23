# Week 5 — Microservices

The composite project contains discovery/config servers, an API Gateway with
load-balancing, rate limiting and Resilience4j fallback, user/customer/order/
product/inventory/payment/billing services, and focused account, loan,
greeting and JWT auth services. Every service is independently runnable and
uses Eureka names rather than hard-coded host addresses. Database-backed
services keep their original PostgreSQL profiles; the source and configuration
are intentionally included without requiring a database server for the course
repository verification.

Build without installing Maven globally: `../../mvnw -Dmaven.repo.local=../../.m2/repository -pl week-5/microservices -am package -DskipTests`.
Start discovery first, then config server, services, and finally the gateway.
