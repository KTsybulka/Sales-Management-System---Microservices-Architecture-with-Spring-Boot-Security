# Sales Management System

## Overview
The Sales Management System is a microservices-based application designed to handle the sales process for goods or services. It ensures scalability, modularity, and security by dividing functionality into independent services.

## Microservices
1. **Customer Service**
    - Handles customer registration, login, and role assignment (Admin/Customer).

2. **Product/Service Catalog Service**
    - Manages the catalog of products or services available for sale.

3. **Order Management Service**
    - Processes customer orders and tracks their status. Includes an automatic archiving system for orders older than six months.

4. **Payment Service**
    - Handles customer payments and provides payment status to the Order Management Service.

## Architecture
- **Tech Stack**:
    - Spring Boot (Microservices)
    - REST APIs (Spring RestTemplate)
    - Swagger (API Documentation)
    - Spring Security (Role-based Authorization)
    - Databases: PostgreSQL for each service
    - Docker & Docker Compose (Containerization)
    - GitHub Actions (CI/CD)

## Repository Structure
- Each microservice has its own directory containing source code, `pom.xml`, and Docker configuration.
- Centralized design diagrams and shared resources are stored in `design-diagrams/` and `scripts/`.

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/<organization>/sales-management-system.git
   cd sales-management-system