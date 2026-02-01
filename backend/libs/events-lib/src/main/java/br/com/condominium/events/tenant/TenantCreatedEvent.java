package br.com.condominium.events.tenant;

import java.time.Instant;
import java.util.UUID;

public class TenantCreatedEvent {

    private UUID tenantId;
    private String tenantCode;
    private Instant createdAt;

    public TenantCreatedEvent() {}

    public TenantCreatedEvent(UUID tenantId, String tenantCode, Instant createdAt) {
        this.tenantId = tenantId;
        this.tenantCode = tenantCode;
        this.createdAt = createdAt;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
