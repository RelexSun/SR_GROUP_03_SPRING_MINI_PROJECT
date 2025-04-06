package com.example.gamified_habit_tracker_api.controller;

import com.example.gamified_habit_tracker_api.base.APIResponse;
import com.example.gamified_habit_tracker_api.base.BaseController;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequestUpdate;
import com.example.gamified_habit_tracker_api.model.response.AppUserResponse;
import com.example.gamified_habit_tracker_api.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/profiles")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserProfileController extends BaseController {

    private final UserProfileService userProfileService;

    @GetMapping
    @Operation(summary = "Get user profile")
    public ResponseEntity<APIResponse> getUserProfileById(){
        AppUserResponse appUser;
        appUser = userProfileService.getUserProfileById();
        return response(APIResponse.builder()
                .success(true)
                .message("App User retrieved successfully!")
                .status(HttpStatus.OK)
                .payload(appUser)
                .build());
    }
    @PutMapping
    @Operation(summary = "Update user profile")
    public ResponseEntity<APIResponse> updateUserProfileById(@Valid @RequestBody AppUserRequestUpdate appUserRequestUpdate){
        AppUserResponse appUser;
        appUser = userProfileService.updateUserProfileById(appUserRequestUpdate);
        return response(APIResponse.builder()
                .success(true)
                .message("App User retrieved successfully!")
                .status(HttpStatus.OK)
                .payload(appUser)
                .build());
    }
    @DeleteMapping
    @Operation(summary = "Delete user profile")
    public ResponseEntity<APIResponse> deleteUserProfileById(){
        AppUserResponse appUser = userProfileService.deleteUserProfileById();
        return response(APIResponse.builder()
                .success(true)
                .message("App User deleted successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .build());
    }


}