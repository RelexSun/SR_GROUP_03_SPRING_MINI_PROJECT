package com.example.gamified_habit_tracker_api.service;

import com.example.gamified_habit_tracker_api.model.response.HabitResponse;

public interface HabitService {


    HabitResponse getAllHabits(Integer page, Integer size);
}
