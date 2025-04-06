package com.example.gamified_habit_tracker_api.model.mapper;

import com.example.gamified_habit_tracker_api.model.entities.Habit;
import com.example.gamified_habit_tracker_api.model.response.HabitResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AppUserMapper.class)
public interface HabitMapper {
    @Mapping(target = "appUserResponse", source = "appUser")
    HabitResponse toHabitResponse(Habit habit);

    List<HabitResponse> toHabitResponsesList(List<Habit> habits);
}