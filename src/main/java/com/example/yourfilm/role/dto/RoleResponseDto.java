package com.example.yourfilm.role.dto;

import com.example.yourfilm.permission.entity.Permission;
import com.example.yourfilm.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDto {
    private Role role;
    private List<Permission> excludedPermissions;
}
