package com.example.gamified_habit_tracker_api.service;


import com.example.gamified_habit_tracker_api.model.entities.Achievement;

import java.util.List;

public interface AchievementService {
    List<Achievement> getAllAchievement(Integer page, Integer size);

    List<Achievement> getAchievementByUserId(Integer page, Integer size);
}
