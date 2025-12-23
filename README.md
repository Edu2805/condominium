# 🏢 Condominium

Condominium é uma plataforma em desenvolvimento para gestão de condomínios residenciais, criada com foco em boas práticas de engenharia de software, arquitetura moderna, resiliência e escalabilidade futura.

O projeto nasce a partir de uma necessidade real (gestão de um condomínio com centenas de unidades) e também tem como objetivo servir como portfólio técnico, demonstrando domínio prático de backend, frontend, infraestrutura, mensageria e arquitetura de software.

---

## 🎯 Objetivos do Projeto

- Centralizar informações de moradores, apartamentos e veículos
- Gerenciar vagas de garagem rotativas
- Registrar e controlar encomendas e correspondências
- Gerenciar reservas de áreas comuns (ex: salão de festas)
- Enviar eventos para empresas de segurança
- Enviar notificações aos moradores
- Ser reproduzível, resiliente e evolutivo
- Permitir futura comercialização e escalabilidade

---

## 🧠 Princípios e Decisões de Arquitetura

- Separação clara de responsabilidades
- Domain-Driven Design (DDD) com Bounded Contexts
- Princípios SOLID
- Infraestrutura como código
- Containers como unidade padrão de execução
- Banco de dados versionado exclusivamente via migrations
- Arquitetura preparada para evolução:
  - Monólito modular inicialmente
  - Possível evolução para microserviços no futuro

---

## 🏗️ Arquitetura Geral (Visão Atual)

### Backend
- Java + Spring Boot
- Monólito modular baseado em DDD
- Serviços planejados:
  - Auth Service (JWT)
  - Condominium Service (regras de negócio)
  - Migration Service (Flyway)
  - Messaging Service (planejado)

### Frontend
- Angular

### Banco de Dados
- PostgreSQL

### Mensageria e Cache (Planejado)
- RabbitMQ
- Redis

### Infraestrutura
- Docker
- Docker Compose
- NGINX (planejado)

---

## 📦 Tecnologias Utilizadas

### Backend
- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Flyway
- RabbitMQ
- Redis

### Frontend
- Angular
- TypeScript

### Banco de Dados
- PostgreSQL

### Infraestrutura
- Docker
- Docker Compose
- NGINX

### Qualidade e DevOps
- Git
- GitLab CI
- SonarQube

---

## 🗂️ Organização do Repositório

condominium  
├── backend  
│   ├── auth-service  
│   ├── condominio-service  
│   └── migrations  
│       └── sql  
├── frontend  
│   └── webapp  
├── infra  
│   ├── docker-compose.yml  
│   └── .env  
├── README.md  
└── .gitignore  

---

## 🗄️ Banco de Dados e Migrations

O banco de dados do projeto é criado e evoluído exclusivamente por meio de migrations versionadas utilizando Flyway.

Todas as alterações estruturais do banco são realizadas por arquivos de migration, garantindo histórico, rastreabilidade e reprodutibilidade do ambiente. Nenhuma alteração de schema é feita manualmente.

As migrations são executadas por um serviço dedicado, separado dos serviços de backend. Os serviços de negócio apenas consomem o schema já existente, evitando acoplamento e conflitos de versão.

Esse modelo garante:
- Ambientes previsíveis
- Facilidade de onboarding
- Histórico claro de evolução do banco
- Segurança na evolução do schema

---

## 🐳 Execução do Projeto (Stack Completa)

### Pré-requisitos
- Docker
- Docker Compose

### Subir toda a stack
1. Clonar o repositório
2. Acessar a pasta infra
3. Executar o Docker Compose

Com isso, a stack completa é inicializada, incluindo:
- PostgreSQL
- Serviço de migrations
- Backend
- Frontend

Todo o ambiente é criado de forma automatizada e reproduzível.

---

## 🔄 Roadmap de Desenvolvimento

### Fase 1 — Infraestrutura Base
- Docker
- Docker Compose
- PostgreSQL em container
- Volumes persistentes
- Organização inicial do projeto

### Fase 2 — Banco e Domínio
- Serviço de migrations (Flyway)
- Modelagem inicial do domínio
- Migrations versionadas
- Seed de dados para ambiente de demonstração

### Fase 3 — Backend
- Serviço de autenticação com JWT
- Serviço principal do condomínio
- DDD com módulos por contexto
- Regras de negócio e validações
- Integração completa com banco

### Fase 4 — Frontend
- Aplicação Angular
- Autenticação
- Telas administrativas
- Integração com backend

### Fase 5 — Mensageria e Resiliência
- RabbitMQ
- Persistência de mensagens
- Envio de eventos externos
- Retry e tolerância a falhas

### Fase 6 — Qualidade e DevOps
- SonarQube
- Pipeline CI/CD
- Análise de qualidade de código
- Build e deploy automatizados

---

## 🚀 Visão de Futuro

- Evolução para sistema multi-condomínio
- Escalabilidade horizontal
- Separação de contextos críticos
- Observabilidade e monitoramento
- Preparação para uso comercial

---

## 👤 Autor

Projeto desenvolvido por Eduardo Amorim  
Software Developer  
Java | Spring Boot | Angular  

Projeto desenvolvido de forma incremental, sem prazo fixo, com foco em aprendizado contínuo, boas práticas e alinhamento com padrões de mercado.

