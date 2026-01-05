# Auth Service Backend

Serviço de autenticação da plataforma **Condominium**.

Este serviço é responsável por centralizar toda a lógica de **autenticação e autorização** da aplicação, funcionando de forma independente e preparada para evolução futura em arquitetura de microserviços.

---

## Objetivo

O **auth-service-backend** tem como objetivo:

- Gerenciar autenticação de usuários
- Emitir e validar tokens JWT
- Controlar autorização baseada em perfis (roles)
- Centralizar regras de segurança da plataforma
- Integrar futuramente com mensageria (RabbitMQ) para eventos de domínio

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.9**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker**
- **Maven**

---

## Arquitetura

Este serviço segue boas práticas de:

- **DDD (Domain-Driven Design)**
- **SOLID**
- **Separação de responsabilidades**
- **Preparação para microsserviços**

Inicialmente, ele será executado como parte de uma stack Docker, mas foi projetado para funcionar de forma totalmente independente.

---

## Estrutura Inicial do Projeto

```
auth-service-backend
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.condominium.auth
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── domain
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       └── security
│   │   └── resources
│   │       └── application.yml
│   └── test
├── Dockerfile
├── pom.xml
└── README.md
```

---

## Execução

A execução será feita via **Docker**, integrada ao `docker-compose` do projeto **Condominium**.

Detalhes completos de execução e variáveis de ambiente serão adicionados conforme o serviço evoluir.

---

## Status do Projeto

🚧 **Em desenvolvimento**

Este serviço está em fase inicial e faz parte de um projeto maior voltado para:
- Aprendizado contínuo
- Boas práticas de engenharia de software
- Portfólio profissional

---

## Projeto Relacionado

Este serviço faz parte do projeto **Condominium**, uma plataforma para gestão de condomínios desenvolvida com foco em:

- Escalabilidade
- Segurança
- Qualidade de código
- Observabilidade
- Automação de pipelines

---

## Autor

**Eduardo Amorim**  
Software Developer  
Projeto desenvolvido para estudo, portfólio e evolução profissional.
