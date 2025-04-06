package com.example.gamified_habit_tracker_api.service.implementation;

import com.example.gamified_habit_tracker_api.config.AppConfig;
import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequestUpdate;
import com.example.gamified_habit_tracker_api.repository.UserProfileRepository;
import com.example.gamified_habit_tracker_api.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImplementation implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final AppConfig appConfig;
    public AppUser getCurrentUser() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @Override
    public AppUser getUserProfileById() {
        AppUser appUser = getCurrentUser();
        return userProfileRepository.getUserProfileById(appUser.getAppUserId().toString());
    }

    @Override
    public AppUser updateUserProfileById(AppUserRequestUpdate appUserRequestUpdate) {
        AppUser appUser = getCurrentUser();
        return userProfileRepository.updateUserProfileById( appUser.getAppUserId().toString() , appUserRequestUpdate);
    }

    @Override
    public AppUser deleteUserProfileById() {
        AppUser appUser = getCurrentUser();
        return userProfileRepository.deleteUserProfileById( appUser.getAppUserId().toString());
    }
}
