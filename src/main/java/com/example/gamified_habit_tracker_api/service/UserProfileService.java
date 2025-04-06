package com.example.gamified_habit_tracker_api.service;

import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequestUpdate;
import jakarta.validation.Valid;

public interface UserProfileService {
    AppUser getUserProfileById();

    AppUser updateUserProfileById(@Valid AppUserRequestUpdate appUserRequestUpdate);

    AppUser deleteUserProfileById();
}