package com.example.gamified_habit_tracker_api.controller;

import com.example.gamified_habit_tracker_api.base.APIResponse;
import com.example.gamified_habit_tracker_api.base.BaseController;
import com.example.gamified_habit_tracker_api.service.HabitService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/habits")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class HabitController extends BaseController{
    private final HabitService habitService;

    public ResponseEntity<APIResponse> getAllHabits(Integer page, Integer size) {
        return response(APIResponse.builder()
                .success(true)
                .message("Fetched all habits successfully!")
                .status(HttpStatus.OK)
                .payload(habitService.getAllHabits(page, size))
                .build());
    }
}
