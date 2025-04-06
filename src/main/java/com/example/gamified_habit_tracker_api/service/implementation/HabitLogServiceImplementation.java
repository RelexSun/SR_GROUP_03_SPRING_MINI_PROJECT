package com.example.gamified_habit_tracker_api.service.implementation;

import com.example.gamified_habit_tracker_api.exception.NotFoundException;
import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.enums.HabitLogStatus;
import com.example.gamified_habit_tracker_api.model.mapper.HabitLogMapper;
import com.example.gamified_habit_tracker_api.model.request.HabitLogRequest;
import com.example.gamified_habit_tracker_api.model.response.HabitLogResponse;
import com.example.gamified_habit_tracker_api.repository.AppUserRepository;
import com.example.gamified_habit_tracker_api.repository.HabitLogRepository;
import com.example.gamified_habit_tracker_api.repository.HabitRepository;
import com.example.gamified_habit_tracker_api.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitLogServiceImplementation implements HabitLogService {
    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;
    private final AppUserRepository appUserRepository;
    private final HabitLogMapper habitLogMapper;

    @Override
    public List<HabitLogResponse> getAllHabitLogsByHabitId(Integer page, Integer size, UUID habitId) {
        page = (page - 1) * size;
        if(habitRepository.findById(habitId.toString()) == null) throw new NotFoundException("Habit with ID " + habitId + " not found");
        return habitLogMapper.toHabitLogResponseList(habitLogRepository.findAll(page, size, habitId.toString()));
    }

    @Override
    public HabitLogResponse saveHabitLog(HabitLogRequest request) {
        int defaultXpEarned = 0;
        if(habitRepository.findById(request.habitId().toString()) == null) throw new NotFoundException("Habit with ID " + request.habitId() + " not found");
        if(request.status().equals(HabitLogStatus.COMPLETED)){
            defaultXpEarned = 10;
            String appUserId = ((AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAppUserId().toString();
            int currentXp = appUserRepository.updateUserXpById(appUserId, defaultXpEarned);
            if(currentXp % 100 == 0){
                appUserRepository.updateUserLevelById(appUserId);
            }
        }
        return habitLogMapper.toHabitLogResponse(habitLogRepository.save(request, defaultXpEarned));
    }
}
