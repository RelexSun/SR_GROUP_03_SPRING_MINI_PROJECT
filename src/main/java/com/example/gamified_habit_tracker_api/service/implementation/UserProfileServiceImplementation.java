package com.example.gamified_habit_tracker_api.service.implementation;

import com.example.gamified_habit_tracker_api.config.AppConfig;
import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.mapper.AppUserMapper;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequestUpdate;
import com.example.gamified_habit_tracker_api.model.response.AppUserResponse;
import com.example.gamified_habit_tracker_api.repository.UserProfileRepository;
import com.example.gamified_habit_tracker_api.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImplementation implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final AppUserMapper appUserMapper;
    private final AppConfig appConfig;
    public AppUser getCurrentUser() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @Override
    public AppUserResponse getUserProfileById() {
        UUID appUserId = getCurrentUser().getAppUserId();
        return appUserMapper.toResponse(userProfileRepository.getUserProfileById(appUserId.toString()));
    }

    @Override
    public AppUserResponse updateUserProfileById(AppUserRequestUpdate appUserRequestUpdate) {
        AppUser appUser = getCurrentUser();
        return appUserMapper.toResponse(userProfileRepository.updateUserProfileById( appUser.getAppUserId().toString() , appUserRequestUpdate));
    }

    @Override
    public AppUserResponse deleteUserProfileById() {
        AppUser appUser = getCurrentUser();
        return appUserMapper.toResponse(userProfileRepository.deleteUserProfileById( appUser.getAppUserId().toString()));
    }
}
