package br.com.condominium.auth.model.repository;

import br.com.condominium.auth.model.entity.UserTenant;
import br.com.condominium.auth.model.entity.UserTenantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTenantRepository extends JpaRepository<UserTenant, UserTenantId> {
}
