package br.com.condominium.migration.tenant.controller;

import br.com.condominium.migration.tenant.dto.CreateTenantRequest;
import br.com.condominium.migration.tenant.service.TenantProvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantProvisionService provisionService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTenantRequest request) {
        provisionService.provision(request.code());
        return ResponseEntity.accepted().build();
    }
}
