package com.example.gamified_habit_tracker_api.repository;

import com.example.gamified_habit_tracker_api.config.UuidTypeHandler;
import com.example.gamified_habit_tracker_api.model.entities.Habit;
import com.example.gamified_habit_tracker_api.model.request.HabitRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
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
        SELECT * FROM habits WHERE app_user_id = #{appUserId}::uuid OFFSET #{page} LIMIT #{size};
    """)
    List<Habit> findAll(Integer page, Integer size, String appUserId);

    @ResultMap("habitMapper")
    @Select("""
        SELECT * FROM habits WHERE habit_id = #{habitId}::uuid;
    """)
    Habit findById(String habitId);

    @ResultMap("habitMapper")
    @Select("""
        INSERT INTO habits VALUES (DEFAULT, #{request.title}, #{request.description}, #{request.frequency}, DEFAULT, #{appUserId}::uuid)
        RETURNING *;
    """)
    Habit save(String appUserId, @Param("request") HabitRequest request);

    @ResultMap("habitMapper")
    @Select("""
        UPDATE habits SET title = #{request.title}, description = #{request.description}, frequency = #{request.frequency} WHERE habit_id = #{habitId}::uuid
        RETURNING *;
    """)
    Habit update(String habitId, @Param("request") HabitRequest request);

    @Delete("""
        DELETE FROM habits WHERE habit_id = #{habitId}::uuid;
    """)
    Integer remove(String habitId);
}