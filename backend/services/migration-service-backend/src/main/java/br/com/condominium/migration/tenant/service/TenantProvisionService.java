package br.com.condominium.migration.tenant.service;

import br.com.condominium.migration.tenant.event.TenantProvisioned;
import br.com.condominium.migration.tenant.model.Tenant;
import br.com.condominium.migration.tenant.repository.TenantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantProvisionService {

    private final TenantRepository tenantRepository;
    private final RabbitTemplate rabbitTemplate;
    private final DataSource dataSource;

    @Transactional
    public void provision(String code) {

        if (tenantRepository.findByCode(code).isPresent()) {
            return;
        }

        UUID tenantId = UUID.randomUUID();
        String schemaName = "condominium_" + code;

        createSchema(schemaName);
        runMigrations(schemaName);

        Tenant tenant = Tenant.builder()
                .id(tenantId)
                .code(code)
                .schemaName(schemaName)
                .createdAt(LocalDateTime.now())
                .build();

        tenantRepository.save(tenant);

        publishProvisionedEvent(tenantId, code);
    }

    private void createSchema(String schemaName) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE SCHEMA IF NOT EXISTS ".concat(schemaName));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void runMigrations(String schemaName) {

        Flyway.configure()
                .dataSource(dataSource)
                .schemas(schemaName)
                .load()
                .migrate();
    }

    private void publishProvisionedEvent(UUID tenantId, String code) {

        rabbitTemplate.convertAndSend(
                "tenant.exchange",
                "tenant.provisioned",
                new TenantProvisioned(tenantId, code)
        );
    }
}
