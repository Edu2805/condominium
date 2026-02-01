# security-lib-backend

Biblioteca compartilhada de segurança para os serviços do sistema **Condominium**.

Esta lib centraliza:
- Geração e validação de JWT
- Contratos de claims padrão
- Tipos de token (ACCESS, INTERNAL, etc.)

❗ A lib **não contém regras de negócio** (expiração, login, autorização por endpoint).
Essas regras pertencem aos serviços consumidores.

---

## 🎯 Objetivo

Evitar duplicação de código de segurança entre serviços e garantir:
- Padrão único de JWT
- Compatibilidade entre tokens emitidos e consumidos
- Evolução controlada da segurança

---

## 📦 Conteúdo da Lib

### 🔐 JWT

- `JwtService`
- `DefaultJwtService`
- `JwtPayload`
- `JwtClaims`
- `TokenType`

A lib **assina, valida e interpreta tokens**.

---

## 🧠 Responsabilidades

### ✔️ A lib faz
- Assinar JWT (HMAC SHA-256)
- Validar assinatura
- Extrair claims
- Verificar expiração (via parsing)

### ❌ A lib NÃO faz
- Definir tempo de expiração
- Autenticar usuários
- Controlar permissões
- Criar filtros HTTP
- Integrar com Spring Security
