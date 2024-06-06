package com.example.yourfilm.permission;

import com.example.yourfilm.permission.dto.PermissionCreateDto;
import com.example.yourfilm.permission.dto.PermissionResponseDto;
import com.example.yourfilm.permission.entity.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/permission")
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    public String getPermissionsPage(Model model){
        List<Permission> permissions = permissionService.getAllRoles();
        model.addAttribute("permissions", permissions);
        return "permission/permissions";
    }

    @GetMapping("/{id}")
    public String getPermission(@PathVariable UUID id, Model model){
        PermissionResponseDto permissionResponseDto = permissionService.getPermission(id);

        model.addAttribute("permissionResponseDto", permissionResponseDto);
        return "permission/permission";
    }

    @PostMapping
    public String createPermission(@ModelAttribute PermissionCreateDto permissionCreateDto){
        permissionService.create(permissionCreateDto);
        return "redirect:/permission";
    }

    @DeleteMapping("/{id}")
    public String deletePermission(@PathVariable UUID id){
        permissionService.delete(id);
        return "redirect:/permission";
    }
}
