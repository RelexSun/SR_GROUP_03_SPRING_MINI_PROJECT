package com.example.gamified_habit_tracker_api.service;

import com.example.gamified_habit_tracker_api.model.request.AppUserRequest;
import com.example.gamified_habit_tracker_api.model.response.AppUserResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    AppUserResponse register(@Valid AppUserRequest request);
}

