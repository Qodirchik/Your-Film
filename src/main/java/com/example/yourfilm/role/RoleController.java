package com.example.yourfilm.role;

import com.example.yourfilm.role.dto.RoleCreateDto;
import com.example.yourfilm.role.dto.RoleResponseDto;
import com.example.yourfilm.role.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    @GetMapping
    public String getRolePage(Model model){
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "role/roles";
    }

    @GetMapping("/{id}")
    public String getRole(@PathVariable UUID id, Model model){
        RoleResponseDto roleResponseDto = roleService.getRole(id);
        model.addAttribute("roleResponseDto", roleResponseDto);
        return "role/role";
    }

    @PostMapping
    public String createRole(@ModelAttribute RoleCreateDto roleCreateDto){
        roleService.create(roleCreateDto);
        return "redirect:/role";
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable UUID id){
        roleService.delete(id);
        return "redirect:/role";
    }
}
