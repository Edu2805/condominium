package br.com.condominium.migration.service;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlywayMigrationService {

    private final Flyway flyway;

    @Autowired
    public FlywayMigrationService(Flyway flyway) {
        this.flyway = flyway;
    }

    public void migrate() {
        flyway.migrate();
    }

    public void repair() {
        flyway.repair();
    }

    public void clean() {
        flyway.clean();
    }
}
