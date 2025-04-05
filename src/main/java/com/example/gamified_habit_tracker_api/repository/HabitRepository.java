package com.example.gamified_habit_tracker_api.repository;

import com.example.gamified_habit_tracker_api.config.UuidTypeHandler;
import com.example.gamified_habit_tracker_api.model.entities.Habit;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Mapper
public interface HabitRepository {

    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", jdbcType = JdbcType.OTHER, javaType = UUID.class, typeHandler = UuidTypeHandler.class, column = "habit_id"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "appUser", column = "app_user_id",
                    one = @One(select = "com.example.gamified_habit_tracker_api.repository.AppUserRepository.getUserById")),
            @Result(property = "createdAt", column = "created_at")
    })
    @Select("""
        SELECT * FROM habits WHERE habit_id = #{habitId} OFFSET #{offset} LIMIT #{limit};
    """)
    Habit findAll(Integer page, Integer size, UUID habitId);
}
