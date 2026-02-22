package br.com.condominium.events.core;

import java.time.Instant;
import java.util.UUID;

public record EventMetadata(
        UUID eventId,
        String eventType,
        String version,
        Instant occurredAt
) {

    public static EventMetadata of(String eventType, String version) {
        return new EventMetadata(
                UUID.randomUUID(),
                eventType,
                version,
                Instant.now()
        );
    }
}
