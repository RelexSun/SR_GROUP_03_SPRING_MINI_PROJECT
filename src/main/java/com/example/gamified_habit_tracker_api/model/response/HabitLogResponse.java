package com.example.gamified_habit_tracker_api.model.response;

import com.example.gamified_habit_tracker_api.model.enums.HabitLogStatus;

import java.util.Date;
import java.util.UUID;

public record HabitLogResponse(
        UUID habitLogId,
        Date logDate,
        HabitLogStatus status,
        Integer xpEarned,
        HabitResponse habit
) {
}
