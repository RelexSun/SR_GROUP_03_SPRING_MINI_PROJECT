package com.example.gamified_habit_tracker_api.controller;

import com.example.gamified_habit_tracker_api.base.APIResponse;
import com.example.gamified_habit_tracker_api.base.BaseController;
import com.example.gamified_habit_tracker_api.model.entities.Achievement;
import com.example.gamified_habit_tracker_api.service.AchievementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/achievements")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AchievementController extends BaseController {
    private final AchievementService achievementService;

    @GetMapping
    @Operation(summary = "Get all achievements")
    public ResponseEntity<APIResponse> getAllAchievement(
            @RequestParam(defaultValue = "1") @Positive Integer page ,
            @RequestParam(defaultValue = "10") @Positive Integer size) {
        List<Achievement> achievements;
        achievements = achievementService.getAllAchievement(page,size);
        return response(APIResponse.builder()
                .success(true)
                .message("Achievements retrieved successfully!")
                .status(HttpStatus.OK)
                .payload(achievements)
                .build());
    }

    @GetMapping("/app-users")
    @Operation(summary = "Get achievement by App User ID")
    public ResponseEntity<APIResponse> getAchievementByUserId(
            @RequestParam(defaultValue = "1") @Positive Integer page,
            @RequestParam(defaultValue = "10") @Positive Integer size){
        List<Achievement> achievements;
        achievements = achievementService.getAchievementByUserId(page,size);
        return response(APIResponse.builder()
                .success(true)
                .message("Achievements for the specified App User retrieved successfully!")
                .status(HttpStatus.OK)
                .payload(achievements)
                .build());
    }
}
