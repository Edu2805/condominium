package br.com.condominium.security.jwt;

import java.time.Instant;
import java.util.UUID;

public record JwtPayload(
        UUID userId,
        UUID tenantId,
        String role,
        TokenType tokenType,
        Instant issuedAt,
        Instant expiresAt
) {}
