package com.example.gamified_habit_tracker_api.model.mapper;


import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.response.AppUserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserResponse toResponse(AppUser user);
}

