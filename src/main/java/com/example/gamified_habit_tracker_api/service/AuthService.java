package com.example.gamified_habit_tracker_api.service;

import com.example.gamified_habit_tracker_api.model.request.AppUserRequest;
import com.example.gamified_habit_tracker_api.model.request.AuthRequest;
import com.example.gamified_habit_tracker_api.model.response.AppUserResponse;
import com.example.gamified_habit_tracker_api.model.response.AuthResponse;
import jakarta.mail.MessagingException;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest) throws Exception;

    AppUserResponse register(AppUserRequest appUserRequest) throws MessagingException;

    void verify(String optCode);

    void resend(String email) throws MessagingException;
}
