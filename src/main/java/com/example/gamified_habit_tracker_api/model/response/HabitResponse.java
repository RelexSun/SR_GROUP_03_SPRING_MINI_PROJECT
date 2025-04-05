package com.example.gamified_habit_tracker_api.model.response;

import com.example.gamified_habit_tracker_api.model.enums.Frequency;

import java.time.LocalDateTime;
import java.util.UUID;

public record HabitResponse(
        UUID habitId,
        String title,
        String description,
        Frequency frequency,
        Boolean isActive,
        AppUserResponse appUser,
        LocalDateTime createdAt
) {
}
