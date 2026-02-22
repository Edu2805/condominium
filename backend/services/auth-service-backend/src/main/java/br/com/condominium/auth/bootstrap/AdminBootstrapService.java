package br.com.condominium.auth.bootstrap;

import br.com.condominium.auth.model.entity.Credential;
import br.com.condominium.auth.model.entity.Role;
import br.com.condominium.auth.model.entity.RolePermission;
import br.com.condominium.auth.model.entity.RolePermissionId;
import br.com.condominium.auth.model.entity.User;
import br.com.condominium.auth.model.entity.UserTenant;
import br.com.condominium.auth.model.entity.UserTenantId;
import br.com.condominium.auth.model.repository.CredentialRepository;
import br.com.condominium.auth.model.repository.PermissionRepository;
import br.com.condominium.auth.model.repository.RolePermissionRepository;
import br.com.condominium.auth.model.repository.RoleRepository;
import br.com.condominium.auth.model.repository.UserRepository;
import br.com.condominium.auth.model.repository.UserTenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminBootstrapService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final UserTenantRepository userTenantRepository;
    private final CredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createAdminForTenant(UUID tenantId) {

        Role adminRole = roleRepository
                .findByNameAndTenantId("ADMIN", tenantId)
                .orElseGet(() -> roleRepository.save(
                        new Role(
                                UUID.randomUUID(),
                                tenantId,
                                "ADMIN",
                                "Role with all permissions for tenant " + tenantId
                        )
                ));

        var permissions = permissionRepository.findAll();

        permissions.forEach(permission -> {

            RolePermissionId rpId =
                    new RolePermissionId(adminRole.getId(), permission.getId());

            if (!rolePermissionRepository.existsById(rpId)) {
                rolePermissionRepository.save(
                        RolePermission.builder()
                                .id(rpId)
                                .build()
                );
            }
        });

        String adminEmail = "admin@" + tenantId + ".local";

        User adminUser = userRepository
                .findByEmail(adminEmail)
                .orElseGet(() -> userRepository.save(
                        new User(
                                UUID.randomUUID(),
                                adminEmail,
                                true,
                                LocalDateTime.now(),
                                LocalDateTime.now()
                        )
                ));

        if (!credentialRepository.existsById(adminUser.getId())) {

            String rawPassword =
                    UUID.randomUUID().toString().substring(0, 12);

            credentialRepository.save(
                    Credential.builder()
                            .userId(adminUser.getId())
                            .passwordHash(passwordEncoder.encode(rawPassword))
                            .passwordExpiresAt(LocalDateTime.now().plusDays(1))
                            .build()
            );
        }

        UserTenantId utId =
                new UserTenantId(adminUser.getId(), tenantId);

        if (!userTenantRepository.existsById(utId)) {
            userTenantRepository.save(
                    UserTenant.builder()
                            .id(utId)
                            .roleId(adminRole.getId())
                            .build()
            );
        }
    }
}
