package com.example.yourfilm.role;

import com.example.yourfilm.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    List<Role> findAllByIdNotIn(List<UUID> ids);
}
