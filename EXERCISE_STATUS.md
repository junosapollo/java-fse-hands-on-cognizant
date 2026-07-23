# Exercise status and traceability

The schedule in `DN 4.0 - Java FSE Mandatory hands-on detail.xlsx` was used as
the source of truth. The table below records where each scheduled topic lives
and how it can be checked.

| Week | Workbook/PDF topics | Implementation | Verification |
|---|---|---|---|
| 1 | Algorithms & Data Structures; Design Patterns & Principles | `week-1/engineering-concepts` (the moved `DataStructures` and `DesignPatterns` source trees) | `./mvnw -pl week-1/engineering-concepts test` |
| 2 | PL/SQL Exercises | `week-2/plsql-banking` — schema, fixtures, control/error/procedure/function/trigger/cursor/package scripts | SQL*Plus/SQLcl run order in its README; `99_verify.sql` |
| 2 | JUnit Basic and Advanced; Mockito; Spring Test; mock dependencies; SLF4J Logging | `week-2/java-testing-lab` | `./mvnw -pl week-2/java-testing-lab test` |
| 3 | Spring Core & Maven | `week-3/spring-core-library-management` — XML, constructor/setter/annotation DI and AOP | `./mvnw -pl week-3/spring-core-library-management test` |
| 3 | Spring Data JPA/Hibernate exercises 1–3 and summary | `week-3/orm-learn` — country, stock, payroll and quiz mappings/queries | `./mvnw -pl week-3/orm-learn test` |
| 3 | Employee Management System | `week-3/employee-management-system` — REST/JPA, projections, validation, auditing, batch and optional audit datasource | `./mvnw -pl week-3/employee-management-system test` |
| 3 | Spring Boot library management | `week-3/library-management-boot` | `./mvnw -pl week-3/library-management-boot test` |
| 4 | Spring REST exercises 1–4 and JWT | `week-4/spring-learn` — REST resources, validation, exception handling, Basic-to-JWT authentication | `./mvnw -pl week-4/spring-learn test` |
| 5 | Sample microservices, load balancing, Spring Boot 3, API gateway, composite hand-on | `week-5/microservices` — discovery/config servers, gateway, service-to-service clients, resilience/rate limits, account/loan/greeting/auth examples | `./mvnw -pl week-5/microservices -am package -DskipTests` |
| 6 | ReactJS HOL 1–8 | `week-6/hol-01-*` through `week-6/hol-08-*` | `./scripts/verify-react.sh` |

React HOL 9–19 and Git HOL 1–5 are intentionally not included: the supplied
workbook places them after week 6. Existing work was moved into the matching
week directory rather than copied, so there is one canonical source tree per
exercise.

## Local-only dependencies

`mvnw` downloads Maven into `.maven/` only when a system Maven is unavailable,
and all Maven artifacts used for verification are directed to `.m2/` by the
commands above. React installs use each app's local `node_modules` and the
repository-local `.npm-cache/`. These directories are ignored and no package
manager writes to the system installation.
