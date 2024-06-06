package com.example.yourfilm.user;

import com.example.yourfilm.permission.PermissionRepository;
import com.example.yourfilm.permission.entity.Permission;
import com.example.yourfilm.role.RoleRepository;
import com.example.yourfilm.role.entity.Role;
import com.example.yourfilm.security.dto.RegisterDto;
import com.example.yourfilm.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new BadCredentialsException("Username or password is not correct"));
    }

    @Transactional
    public void register(RegisterDto registerDto) {
        List<Role> roleRepositoryAll = roleRepository.findAll();
        List<Permission> permissionRepositoryAll = permissionRepository.findAll();
        User user = new User(null, registerDto.getFirstName(), registerDto.getLastName(), registerDto.getPhoneNumber(), registerDto.getUsername(),
                registerDto.getEmail(), registerDto.getPassword(), registerDto.getBirthDate(), new HashSet<>(roleRepositoryAll), new HashSet<>(permissionRepositoryAll), new HashSet<>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
