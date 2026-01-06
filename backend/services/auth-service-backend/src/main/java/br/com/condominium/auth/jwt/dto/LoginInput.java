package br.com.condominium.auth.jwt.dto;

import java.util.UUID;

public record LoginInput(
        String email,
        String password,
        UUID tenantId
) {}
