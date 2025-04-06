package com.example.gamified_habit_tracker_api.service;

import com.example.gamified_habit_tracker_api.model.request.AppUserRequestUpdate;
import com.example.gamified_habit_tracker_api.model.response.AppUserResponse;
import jakarta.validation.Valid;

public interface UserProfileService {
    AppUserResponse getUserProfileById();

    AppUserResponse updateUserProfileById(@Valid AppUserRequestUpdate appUserRequestUpdate);

    AppUserResponse deleteUserProfileById();
}