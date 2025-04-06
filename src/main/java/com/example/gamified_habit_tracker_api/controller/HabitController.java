package com.example.gamified_habit_tracker_api.controller;

import com.example.gamified_habit_tracker_api.base.APIResponse;
import com.example.gamified_habit_tracker_api.base.BaseController;
import com.example.gamified_habit_tracker_api.model.request.HabitRequest;
import com.example.gamified_habit_tracker_api.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class HabitController extends BaseController{
    private final HabitService habitService;

    @GetMapping
    @Operation(summary = "Get all habits")
    public ResponseEntity<APIResponse> getAllHabits(
            @RequestParam(defaultValue = "1") @Positive Integer page,
            @RequestParam(defaultValue = "10") @Positive Integer size) {
        return response(APIResponse.builder()
                .success(true)
                .message("Fetched all habits successfully!")
                .status(HttpStatus.OK)
                .payload(habitService.getAllHabits(page, size)).build());
    }

    @GetMapping("/{habit-id}")
    @Operation(summary = "Get habit by ID")
    public ResponseEntity<APIResponse> getHabitById(@PathVariable("habit-id") UUID habitId) {
        return response(APIResponse.builder()
                .success(true)
                .message("Habit fetched successfully!")
                .status(HttpStatus.OK)
                .payload(habitService.getHabitById(habitId)).build());
    }

    @PostMapping
    @Operation(summary = "Create a new habit")
    public ResponseEntity<APIResponse> saveHabit(@Valid @RequestBody HabitRequest request) {
        return response(APIResponse.builder()
                .success(true)
                .message("Habit created successfully!")
                .status(HttpStatus.CREATED)
                .payload(habitService.saveHabit(request)).build());
    }

    @PutMapping("/{habit-id}")
    @Operation(summary = "Update habit by ID")
    public ResponseEntity<APIResponse> updateHabitById(
            @PathVariable("habit-id") UUID habitId,
            @Valid @RequestBody HabitRequest request
    ){
        return response(APIResponse.builder()
                .success(true)
                .message("Habit updated successfully!")
                .status(HttpStatus.OK)
                .payload(habitService.updateHabitById(habitId, request)).build());
    }

    @DeleteMapping("/{habit-id}")
    @Operation(summary = "Delete habit by ID")
    public ResponseEntity<APIResponse> removeHabitById(@PathVariable("habit-id") UUID habitId){
        habitService.removeHabitById(habitId);
        return response(APIResponse.builder()
                .success(true)
                .message("Habit deleted successfully!")
                .status(HttpStatus.OK).build());
    }
}
