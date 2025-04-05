package com.example.gamified_habit_tracker_api.repository;

import com.example.gamified_habit_tracker_api.config.UuidTypeHandler;
import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
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


    @Select("""
        SELECT * FROM app_users WHERE username = #{username};
    """)
    AppUser getUserByUsername(String username);

//    @Select("""
//        SELECT name FROM app_roles ar INNER JOIN user_role ur ON
//        ar.role_id = ur.role_id WHERE user_id = #{userId};
//    """)
//    List<String> getAllRolesByUserId(Long userId);

//    @Select("""
//       INSERT INTO app_roles VALUES (#{userId}, #{role});
//    """)
//    void createRolesByUserId(Long userId, String role);

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
        SELECT * FROM app_users WHERE app_user_id = #{appUserId};
        RETURNING *;
    """)
    AppUser getUserById(UUID id);
}
