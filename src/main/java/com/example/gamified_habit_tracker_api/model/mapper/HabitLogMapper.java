package com.example.gamified_habit_tracker_api.model.mapper;

import com.example.gamified_habit_tracker_api.model.entities.HabitLog;
import com.example.gamified_habit_tracker_api.model.response.HabitLogResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = HabitMapper.class)
public interface HabitLogMapper {
    HabitLogResponse toHabitLogResponse(HabitLog habitLog);

    List<HabitLogResponse> toHabitLogResponseList(List<HabitLog> habitLogList);
}
