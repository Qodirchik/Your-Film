package com.example.yourfilm.role.dto;

import com.example.yourfilm.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateDto {
    private String name;
    private Set<User> users;
}
