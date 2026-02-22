package br.com.condominium.migration.tenant.event;

import java.util.UUID;

public record TenantProvisioned(UUID tenantId, String code) {
}
