package com.example.gamified_habit_tracker_api.repository;

import com.example.gamified_habit_tracker_api.config.UuidTypeHandler;
import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Mapper
public interface AppUserRepository {
    @Results(id = "appUserMapper", value = {
            @Result(property = "appUserId",jdbcType = JdbcType.OTHER,javaType = UUID.class,typeHandler = UuidTypeHandler.class, column = "app_user_id"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at"),
    })
    @Select("""
        SELECT * FROM app_users WHERE email = #{email};
    """)
    AppUser getUserByEmail(String email);

    @ResultMap("appUserMapper")
    @Select("""
        SELECT * FROM app_users WHERE username = #{username};
    """)
    AppUser getUserByUsername(String username);

    @ResultMap("appUserMapper")
    @Select("""
        INSERT INTO app_users (username, email, password, profile_image_url)
                                    VALUES
        (#{req.username}, #{req.email}, #{req.password}, #{req.profileImageUrl})
        RETURNING *;
    """)
    AppUser register(@Param("req") AppUserRequest request);

    @ResultMap("appUserMapper")
    @Select("""
        SELECT * FROM app_users WHERE app_user_id = #{id}::uuid;
    """)
    AppUser getUserById(String id);

    @Select("""
        UPDATE app_users SET xp = xp + #{xp} WHERE app_user_id = #{appUserId}::uuid RETURNING xp;
    """)
    Integer updateUserXpById(String appUserId, Integer xp);

    @Update("""
        UPDATE app_users SET level = (level + 1) WHERE app_user_id = #{appUserId}::uuid;
    """)
    void updateUserLevelById(String appUserId);

    @ResultMap("appUserMapper")
    @Update("""
         UPDATE app_users SET is_verified = true WHERE email = email;
    """)
    void updateVerificationStatus(String email);
}
