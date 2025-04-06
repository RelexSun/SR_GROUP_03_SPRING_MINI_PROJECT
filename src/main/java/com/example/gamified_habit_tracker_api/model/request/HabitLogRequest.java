package com.example.gamified_habit_tracker_api.model.request;

import com.example.gamified_habit_tracker_api.model.enums.HabitLogStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record HabitLogRequest (
    @NotNull
    HabitLogStatus status,

    @NotNull
    UUID habitId
){
}