package com.example.yourfilm.permission.dto;

import com.example.yourfilm.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionCreateDto {
    private String name;
    private Set<Role> roles;
}
