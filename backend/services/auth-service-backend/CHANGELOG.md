# CHANGELOG AUTH SERVICE BACKEND

## Alterações não lançadas

Jan 05, 2026
- [auth-service-feature/001] - Configurações iniciais do projeto.

Jan 06, 2026
- [auth-service-feature/002] - Configuração inicial do auth-service com Spring Boot e JWT.
  - Estruturação do auth-service com Spring Boot 3.5.x.
  - Configuração de profiles (dev/prod) com uso de variáveis de ambiente.
  - Definição do schema `auth` e modelo de dados base para autenticação e multi-tenant.
  - Criação das entidades JPA: User, Tenant, Credential e UserTenant com chave composta.
  - Definição de roles como enumeração.
  - Configuração inicial de Spring Security com autenticação via JWT.
  - Implementação da geração e validação de JWT com claims customizados (userId, tenantId, role).
  - Inclusão das dependências:
      - spring-boot-starter-security
      - spring-boot-starter-oauth2-resource-server
      - spring-security-oauth2-jose
      - jjwt-api, jjwt-impl, jjwt-jackson
  - Ajustes de build Maven (Java release, encoding e variáveis de execução).
  - Serviço configurado para subir sem execução automática de DDL/DML (migração externa).

Jan 18, 2026
- [auth-service-feature/003] - Alteração do nome da tabela Users para User.