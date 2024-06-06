package com.example.yourfilm.permission;

import com.example.yourfilm.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    List<Permission> findAllByIdNotIn(List<UUID> ids);
}
