package com.example.yourfilm.permission.dto;

import com.example.yourfilm.permission.entity.Permission;
import com.example.yourfilm.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponseDto {
    private Permission permission;
    private List<Role> excludedRoles;
}
