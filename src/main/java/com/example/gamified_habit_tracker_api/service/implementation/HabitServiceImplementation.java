package com.example.gamified_habit_tracker_api.service.implementation;

import com.example.gamified_habit_tracker_api.exception.NotFoundException;
import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.entities.Habit;
import com.example.gamified_habit_tracker_api.model.mapper.HabitMapper;
import com.example.gamified_habit_tracker_api.model.request.HabitRequest;
import com.example.gamified_habit_tracker_api.model.response.HabitResponse;
import com.example.gamified_habit_tracker_api.repository.HabitRepository;
import com.example.gamified_habit_tracker_api.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImplementation implements HabitService {
    private final HabitRepository habitRepository;
    private final HabitMapper habitMapper;

    @Override
    public List<HabitResponse> getAllHabits(Integer page, Integer size) {
        page = (page - 1) * size;
        String appUserId = ((AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUserId().toString();
        return habitMapper.toHabitResponsesList(habitRepository.findAll(page, size, appUserId));
    }

    @Override
    public HabitResponse getHabitById(UUID habitId) {
        Habit habit = habitRepository.findById(habitId.toString());
        if(habit == null) throw new NotFoundException("Habit with ID " + habitId + " not found");
        return habitMapper.toHabitResponse(habit);
    }

    @Override
    public HabitResponse saveHabit(HabitRequest request) {
        String appUserId = ((AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUserId().toString();
        Habit habit = habitRepository.save(appUserId, request);
        if (habit == null) throw new NotFoundException("Failed to create this Habit");
        return habitMapper.toHabitResponse(habit);
    }

    @Override
    public HabitResponse updateHabitById(UUID habitId, HabitRequest request) {
        Habit habit = habitRepository.findById(habitId.toString());
        if(habit == null) throw new NotFoundException("Habit with ID " + habitId + " not found");
        return habitMapper.toHabitResponse(habitRepository.update(habitId.toString(), request));
    }

    @Override
    public void removeHabitById(UUID habitId) {
        if(habitRepository.remove(habitId.toString()).equals(0)) throw new NotFoundException("Habit with ID " + habitId + " not found");
    }
}
