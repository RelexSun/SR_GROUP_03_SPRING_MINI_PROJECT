package com.example.gamified_habit_tracker_api.repository;

import com.example.gamified_habit_tracker_api.config.UuidTypeHandler;
import com.example.gamified_habit_tracker_api.model.entities.Achievement;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AchievementRepository {

    @Results(
            id = "AchievementMapper", value = {
                    @Result(property = "achievementId",jdbcType = JdbcType.OTHER,javaType = UUID.class,typeHandler = UuidTypeHandler.class,column = "achievement_id"),
                    @Result(property = "xpRequired",column = "xp_required")
    }
    )
    @Select("""
        Select * from achievements
        OFFSET #{page} LIMIT #{size}
        ;
    
    """)
    List<Achievement> getAllAchievement(Integer page, Integer size);

    @Select("""
             SELECT a.*
             FROM app_users u
             INNER JOIN achievements a ON a.xp_required <= u.xp
             WHERE app_user_id = #{id}
             OFFSET #{page} LIMIT #{size};
            """)
    @ResultMap("AchievementMapper")
    List<Achievement> getAchievementByUserId(Integer page, Integer size, UUID id);

    @Select("""
            Select achievement_id from achievements
            WHERE xp_required = #{xpRequired}
            """)
    String getAchievementIdByXp(Integer xpRequired);

    @Insert("""
        Insert into app_user_achievement values(default,#{appUserId},#{achievementId}::uuid);
    """)
    @ResultMap("AchievementMapper")
    void saveUserIdAndAchievementId(UUID appUserId, String achievementId);
}
