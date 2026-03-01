package br.com.condominium.migration.orchestrator;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@RequiredArgsConstructor
public class MigrationOrchestrator {

    private final DataSource dataSource;

    @EventListener(ApplicationReadyEvent.class)
    public void migrateShared() {

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .schemas("public")
                .locations("classpath:db/migration/postgres/shared")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }

    public void migrateTenant(String schema) {

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .schemas(schema)
                .locations("classpath:db/migration/postgres/tenant")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}
