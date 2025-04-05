package com.example.gamified_habit_tracker_api.model.mapper;

import com.example.gamified_habit_tracker_api.model.entities.Habit;
import com.example.gamified_habit_tracker_api.model.response.HabitResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HabitMapper {
    HabitResponse toHabitResponse(Habit habit);
}
