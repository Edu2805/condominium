# Migration Service Backend

## 📌 Visão Geral

O **migration-service-backend** é um serviço Spring Boot responsável exclusivamente pelo controle e execução de **migrations de banco de dados** do projeto **Condominium**.

Ele foi projetado para garantir:
- Total controle sobre **DDL/DML**
- Versionamento de schema via **Flyway**
- Execução **sob demanda**, nunca automática
- Separação clara de responsabilidades em relação aos demais serviços (ex: auth-service)

---

## 🎯 Responsabilidades

- Criar schemas por domínio (ex: `auth`, `tenant_x`, etc)
- Executar migrations versionadas
- Reparar histórico de migrations quando necessário
- Preparar a base para novos tenants

⚠️ **Este serviço NÃO contém regras de negócio da aplicação.**

---

## 🧱 Arquitetura

- Java 21
- Spring Boot 3.x
- Flyway (execução manual)
- PostgreSQL
- REST API para disparo de migrations

---

## 🚫 O que este serviço NÃO faz

- ❌ Não usa JPA/Hibernate
- ❌ Não executa migrations no startup
- ❌ Não cria tabelas automaticamente
- ❌ Não gerencia autenticação de usuários finais

---

## 📁 Estrutura do Projeto

```
migration-service-backend
 └── src/main/java
     └── br.com.condominium.migration
         ├── config
         │    └── FlywayConfig.java
         ├── service
         │    └── MigrationService.java
         ├── controller
         │    └── MigrationController.java
         └── MigrationServiceApplication.java
```

```
src/main/resources
 └── db/migration
     └── auth
         ├── V1__create_auth_schema.sql
         ├── V2__create_users.sql
         └── ...
```

---

## ⚙️ Configuração (application.properties)

```properties
spring.application.name=migration-service-backend
server.port=8081

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.flyway.enabled=false
```

As credenciais são fornecidas via **variáveis de ambiente**.

---

## ▶️ Executando Localmente

### 1️⃣ Subir o banco (Docker)

```bash
docker compose up -d postgres
```

### 2️⃣ Rodar o serviço

```bash
./mvnw spring-boot:run
```

### 3️⃣ Disparar migration

```http
POST http://localhost:8081/migrations/migrate
```

---

## 🔐 Segurança (futuro)

- Endpoint será protegido via JWT interno
- Apenas serviços autorizados poderão disparar migrations

---

## 🔮 Evoluções Planejadas

- Execução de migrations via mensageria (RabbitMQ)
- Criação automática de schema por tenant
- Integração com pipelines CI/CD
- Auditoria de execuções

---

## 🧠 Decisões Arquiteturais

- Migrações isoladas em um serviço dedicado
- Total desacoplamento de serviços de negócio
- Alinhado com boas práticas de arquitetura distribuída

---

## 👤 Autor

Eduardo Amorim  
Projeto **Condominium**
