package com.example.gamified_habit_tracker_api.controller;

import com.example.gamified_habit_tracker_api.base.APIResponse;
import com.example.gamified_habit_tracker_api.base.BaseController;
import com.example.gamified_habit_tracker_api.model.request.HabitLogRequest;
import com.example.gamified_habit_tracker_api.service.HabitLogService;
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
@RequestMapping("/api/v1/habit-logs")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class HabitLogController extends BaseController {
    private final HabitLogService habitLogService;

    @GetMapping("/{habit-id}")
    @Operation(summary = "Get all habit logs by habit ID")
    public ResponseEntity<APIResponse> getAllHabitLogsByHabitId(
            @PathVariable("habit-id") UUID habitId,
            @RequestParam(defaultValue = "1") @Positive Integer page,
            @RequestParam(defaultValue = "10") @Positive Integer size
            ){
        return response(APIResponse.builder()
                .success(true)
                .message("Fetched all habit logs for the specified habit ID successfully!")
                .status(HttpStatus.OK)
                .payload(habitLogService.getAllHabitLogsByHabitId(page, size, habitId)).build());
    }

    @PostMapping
    @Operation(summary = "Create a new habit log")
    public ResponseEntity<APIResponse> saveHabitLog(@Valid @RequestBody HabitLogRequest request) {
        return response(APIResponse.builder()
                .success(true)
                .message("Habit log created successfully!")
                .status(HttpStatus.CREATED)
                .payload(habitLogService.saveHabitLog(request)).build());
    }
}
