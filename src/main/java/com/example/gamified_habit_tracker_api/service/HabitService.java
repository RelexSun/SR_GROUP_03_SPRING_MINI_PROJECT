package com.example.gamified_habit_tracker_api.service;

import com.example.gamified_habit_tracker_api.model.request.HabitRequest;
import com.example.gamified_habit_tracker_api.model.response.HabitResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<HabitResponse> getAllHabits(Integer page, Integer size);

    HabitResponse getHabitById(UUID habitId);

    HabitResponse saveHabit(@Valid HabitRequest request);

    HabitResponse updateHabitById(UUID habitId, @Valid HabitRequest request);

    void removeHabitById(UUID habitId);
}