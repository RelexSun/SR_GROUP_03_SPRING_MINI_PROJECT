package com.example.gamified_habit_tracker_api.service;

import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequestUpdate;

public interface UserProfileService {
    AppUser getUserProfileById();

    AppUser updateUserProfileById(AppUserRequestUpdate appUserRequestUpdate);

    AppUser deleteUserProfileById();
}
