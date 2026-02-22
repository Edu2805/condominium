package br.com.condominium.auth.model.repository;

import br.com.condominium.auth.model.entity.RolePermission;
import br.com.condominium.auth.model.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}
