package br.com.condominium.auth.usertenant.repository;

import br.com.condominium.auth.usertenant.model.UserTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTenantRepository extends JpaRepository<UserTenant, UUID> {

    Optional<UserTenant> findByUserIdAndTenantId(UUID userId, UUID tenantId);
}
