package com.example.gamified_habit_tracker_api.repository;

import com.example.gamified_habit_tracker_api.config.UuidTypeHandler;
import com.example.gamified_habit_tracker_api.model.entities.HabitLog;
import com.example.gamified_habit_tracker_api.model.request.HabitLogRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitLogRepository {
    @Results(id = "habitLogMapper", value = {
            @Result(property = "habitLogId", jdbcType = JdbcType.OTHER, javaType = UUID.class, typeHandler = UuidTypeHandler.class, column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habit", column = "habit_id",
                    one = @One(select = "com.example.gamified_habit_tracker_api.repository.HabitRepository.findById"))
    })
    @Select("""
        SELECT * FROM habit_logs WHERE habit_id = #{habitId}::uuid OFFSET #{page} LIMIT #{size};
    """)
    List<HabitLog> findAll(Integer page, Integer size, String habitId);

    @ResultMap("habitLogMapper")
    @Select("""
        INSERT INTO habit_logs VALUES (DEFAULT, DEFAULT, #{request.status}, #{defaultXpEarned}, #{request.habitId})
        RETURNING *;
    """)
    HabitLog save(@Param("request") HabitLogRequest request, Integer defaultXpEarned);
}
