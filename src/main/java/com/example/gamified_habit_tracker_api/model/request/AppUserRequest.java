package com.example.gamified_habit_tracker_api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {
    private String username;
    private String email;
    private String password;
    private String profileImageUrl;
}

