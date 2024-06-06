package com.example.yourfilm.permission;

import com.example.yourfilm.permission.dto.PermissionCreateDto;
import com.example.yourfilm.permission.dto.PermissionResponseDto;
import com.example.yourfilm.permission.entity.Permission;
import com.example.yourfilm.role.RoleRepository;
import com.example.yourfilm.role.entity.Role;
import com.example.yourfilm.user.UserRepository;
import com.example.yourfilm.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public List<Permission> getAllRoles() {
        return permissionRepository.findAll();
    }

    public PermissionResponseDto getPermission(UUID id) {
        Permission permission = permissionRepository.findById(id).orElseThrow();
        List<Role> excludedRole = roleRepository
                .findAllByIdNotIn(permission.getRoles()
                        .stream()
                        .map(Role::getId)
                        .toList());
        return new PermissionResponseDto(permission, excludedRole);

    }

    @Transactional
    public void create(PermissionCreateDto permissionCreateDto) {
        List<Role> roleAll = roleRepository.findAll();
        List<User> userAll = userRepository.findAll();
        Permission permission = new Permission(null, permissionCreateDto.getName(), Collections.emptySet(), new HashSet<>(userAll));
        Permission save = permissionRepository.save(permission);
        roleAll.forEach(role -> role.getPermissions().add(save));
    }

    @Transactional
    public void delete(UUID id) {
        permissionRepository.deleteById(id);
    }
}
