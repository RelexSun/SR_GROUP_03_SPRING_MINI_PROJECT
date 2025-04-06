package com.example.gamified_habit_tracker_api.model.entities;

import com.example.gamified_habit_tracker_api.model.enums.HabitLogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLog {
    private UUID habitLogId;
    private Date logDate;
    private HabitLogStatus status;
    private Integer xpEarned;
    private Habit habit;
}
