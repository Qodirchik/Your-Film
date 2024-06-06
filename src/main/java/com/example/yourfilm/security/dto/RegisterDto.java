package com.example.yourfilm.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String email;
    private String password;
    private String age;
    private LocalDate birthDate;
}
