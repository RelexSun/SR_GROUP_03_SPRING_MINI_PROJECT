package com.example.gamified_habit_tracker_api.service.implementation;

import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.mapper.AppUserMapper;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequest;
import com.example.gamified_habit_tracker_api.model.response.AppUserResponse;
import com.example.gamified_habit_tracker_api.repository.AppUserRepository;
import com.example.gamified_habit_tracker_api.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserImplementation implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        if (identifier.contains("@")) return appUserRepository.getUserByEmail(identifier);
        return appUserRepository.getUserByUsername(identifier);
    }

    @Override
    public AppUserResponse register(AppUserRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        request.setPassword(encoder.encode(request.getPassword()));
        AppUser user = appUserRepository.register(request);

        return appUserMapper.toResponse(user);
    }
}

