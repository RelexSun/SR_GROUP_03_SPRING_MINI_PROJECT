package com.example.gamified_habit_tracker_api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequestUpdate {
    @NotBlank
    private String username;

    @NotBlank
    private String profileImageUrl;
}