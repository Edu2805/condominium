package br.com.condominium.events.tenant;

import br.com.condominium.events.core.EventMetadata;

import java.util.UUID;

public record TenantProvisioned(
        EventMetadata metadata,
        UUID tenantId
) {

    public static TenantProvisioned create(UUID tenantId) {
        return new TenantProvisioned(
                EventMetadata.of("TenantProvisioned", "v1"),
                tenantId
        );
    }
}
