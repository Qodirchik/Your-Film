package com.example.yourfilm.user.entity;

import com.example.yourfilm.permission.entity.Permission;
import com.example.yourfilm.role.entity.Role;
import com.example.yourfilm.video.entity.Video;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String email;
    private String password;
    private LocalDate birthDate;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;
    @ManyToMany(mappedBy = "users")
    private Set<Permission> permissions;
    @ManyToMany(mappedBy = "users")
    private Set<Video> videos;


    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
