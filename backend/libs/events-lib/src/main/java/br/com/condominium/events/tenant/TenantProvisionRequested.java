package br.com.condominium.events.tenant;

import br.com.condominium.events.core.EventMetadata;

import java.util.UUID;

public record TenantProvisionRequested(
        EventMetadata metadata,
        UUID tenantId,
        String tenantCode
) {

    public static TenantProvisionRequested create(UUID tenantId, String tenantCode) {
        return new TenantProvisionRequested(
                EventMetadata.of("TenantProvisionRequested", "v1"),
                tenantId,
                tenantCode
        );
    }
}
