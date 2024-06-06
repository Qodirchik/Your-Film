package com.example.yourfilm.video.dto;

import com.example.yourfilm.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoResponseDto {
    private Set<User> users;
}
