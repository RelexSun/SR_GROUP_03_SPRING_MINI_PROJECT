package com.example.gamified_habit_tracker_api.repository;

import com.example.gamified_habit_tracker_api.config.UuidTypeHandler;
import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequestUpdate;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Mapper
public interface UserProfileRepository {
    @Results(id = "appUserMapper", value = {
            @Result(property = "appUserId",jdbcType = JdbcType.OTHER,javaType = UUID.class,typeHandler = UuidTypeHandler.class, column = "app_user_id"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at"),
    })
    @Select("""
    SELECT * FROM mydb.public.app_users WHERE app_user_id  = #{appUserId}::uuid;
    """)
    AppUser getUserProfileById(String appUserId);


    @Select("""
    UPDATE mydb.public.app_users
    SET username = #{req.username}, profile_image_url = #{req.profileImageUrl}
    WHERE app_user_id = #{UserId}::uuid
    RETURNING *;
    """)
    AppUser updateUserProfileById(String UserId,@Param("req") AppUserRequestUpdate appUserRequestUpdate);


    @Select("""
        Delete FROM mydb.public.app_users where app_user_id  = #{userId}::uuid;
    """)
    AppUser deleteUserProfileById(String userId);
}
