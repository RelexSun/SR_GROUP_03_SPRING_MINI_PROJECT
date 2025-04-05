package com.example.gamified_habit_tracker_api.service.implementation;

import com.example.gamified_habit_tracker_api.config.SecurityConfig;
import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.mapper.HabitMapper;
import com.example.gamified_habit_tracker_api.model.response.HabitResponse;
import com.example.gamified_habit_tracker_api.repository.HabitRepository;
import com.example.gamified_habit_tracker_api.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitServiceImplementation implements HabitService {
    private final HabitRepository habitRepository;
    private final HabitMapper habitMapper;

    @Override
    public HabitResponse getAllHabits(Integer page, Integer size) {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return habitMapper.toHabitResponse(habitRepository.findAll(page, size, appUser.getAppUserId()));
    }
}
