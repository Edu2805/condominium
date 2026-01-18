package br.com.condominium.migration.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlaywayConfig {

    @Bean
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                // migrations organizadas por domínio
                .locations("classpath:db/migration/postgres")
                .baselineOnMigrate(true)
                .load();
    }
}
