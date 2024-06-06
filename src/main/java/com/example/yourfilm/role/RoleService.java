package com.example.yourfilm.role;

import com.example.yourfilm.permission.PermissionRepository;
import com.example.yourfilm.permission.entity.Permission;
import com.example.yourfilm.role.dto.RoleCreateDto;
import com.example.yourfilm.role.dto.RoleResponseDto;
import com.example.yourfilm.role.entity.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public void create(RoleCreateDto roleCreateDto) {
        roleRepository.save(new Role(null, roleCreateDto.getName(), Collections.emptySet(), Collections.emptySet()));
    }

    @Transactional
    public void delete(UUID id) {
        roleRepository.deleteById(id);
    }

    public RoleResponseDto getRole(UUID id) {
        Role role = roleRepository.findById(id).orElseThrow();
        List<Permission> excludedPermission = permissionRepository
                .findAllByIdNotIn(
                        role.getPermissions()
                                .stream()
                                .map(Permission::getId)
                                .toList());
        return new RoleResponseDto(role, excludedPermission);
    }
}
