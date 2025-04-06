package com.example.gamified_habit_tracker_api.service;

import com.example.gamified_habit_tracker_api.model.request.HabitLogRequest;
import com.example.gamified_habit_tracker_api.model.response.HabitLogResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public interface HabitLogService {
    List<HabitLogResponse> getAllHabitLogsByHabitId(@Positive Integer page, @Positive Integer size, UUID habitId);

    HabitLogResponse saveHabitLog(@Valid HabitLogRequest request);
}
