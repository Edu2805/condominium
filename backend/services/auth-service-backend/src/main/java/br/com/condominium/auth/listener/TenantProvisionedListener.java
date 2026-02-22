package br.com.condominium.auth.listener;

import br.com.condominium.auth.bootstrap.AdminBootstrapService;
import br.com.condominium.auth.config.infra.RabbitConfig;
import br.com.condominium.events.tenant.TenantProvisioned;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TenantProvisionedListener {

    private final AdminBootstrapService adminBootstrapService;

    @RabbitListener(
            queues = RabbitConfig.TENANT_PROVISIONED_QUEUE,
            containerFactory = "rabbitListenerContainerFactory"
    )
    public void handle(TenantProvisioned event) {

        log.info("Recebido TenantProvisioned: {}", event.tenantId());

        adminBootstrapService.createAdminForTenant(event.tenantId());
    }
}
