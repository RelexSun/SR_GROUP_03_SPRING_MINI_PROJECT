package com.example.gamified_habit_tracker_api.model.request;

import com.example.gamified_habit_tracker_api.model.enums.HabitFrequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HabitRequest(
        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        HabitFrequency frequency
) {
}
