package com.example.gamified_habit_tracker_api.model.entities;

import com.example.gamified_habit_tracker_api.model.enums.HabitFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habit {
    private UUID habitId;
    private String title;
    private String description;
    private HabitFrequency frequency;
    private Boolean isActive;
    private AppUser appUser;
    private LocalDateTime createdAt;
}