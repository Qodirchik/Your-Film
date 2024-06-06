package com.example.yourfilm.security;

import com.example.yourfilm.security.dto.RegisterDto;
import com.example.yourfilm.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @GetMapping("/register")
    public String getRegister(){
        return "/auth/register";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "/auth/login";
    }

    @PostMapping("/register")
    public String createRegister(@ModelAttribute RegisterDto registerDto){
        userService.register(registerDto);
        return "redirect:auth/login";
    }
}
