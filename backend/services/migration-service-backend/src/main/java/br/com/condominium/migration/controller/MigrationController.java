package br.com.condominium.migration.controller;

import br.com.condominium.migration.service.FlywayMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/migrations")
public class MigrationController {

    private final FlywayMigrationService migrationService;

    @Autowired
    public MigrationController(FlywayMigrationService migrationService) {
        this.migrationService = migrationService;
    }

    @PostMapping("/migrate")
    public ResponseEntity<String> migrate() {
        migrationService.migrate();
        return ResponseEntity.ok("Migration executed successfully");
    }

    @PostMapping("/repair")
    public ResponseEntity<String> repair() {
        migrationService.repair();
        return ResponseEntity.ok("Migration repaired successfully");
    }
}
