package com.example.gamified_habit_tracker_api.service.implementation;

import com.example.gamified_habit_tracker_api.exception.BadRequestException;
import com.example.gamified_habit_tracker_api.exception.NotFoundException;
import com.example.gamified_habit_tracker_api.jwt.JwtService;
import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.model.mapper.AppUserMapper;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequest;
import com.example.gamified_habit_tracker_api.model.request.AuthRequest;
import com.example.gamified_habit_tracker_api.model.response.AppUserResponse;
import com.example.gamified_habit_tracker_api.model.response.AuthResponse;
import com.example.gamified_habit_tracker_api.repository.AppUserRepository;
import com.example.gamified_habit_tracker_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {
    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AppUserMapper appUserMapper;
    private final EmailSenderServiceImplementation emailSenderServiceImplementation;
    private final AppUserImplementation appUserImplementation;

    private void authenticate(String identifier, String password) {
        try {
        AppUser appUser = identifier.contains("@") ?
                appUserRepository.getUserByEmail(identifier)
                : appUserRepository.getUserByUsername(identifier);

        if (appUser == null) {
            throw new NotFoundException("Invalid email");
        }
        if (!passwordEncoder.matches(password, appUser.getPassword())) {
            throw new NotFoundException("Invalid Password");
        }
        if(!appUser.getIsVerified()) {
            throw new BadRequestException("Your account is not verified");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(identifier, password));

        } catch (DisabledException e) {
            throw new BadRequestException("USER_DISABLED" + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new BadRequestException("INVALID_CREDENTIALS" + e.getMessage());
        }
    }


    @SneakyThrows
    @Override
    public AuthResponse login(AuthRequest authRequest) {
        authenticate(authRequest.getIdentifier(), authRequest.getPassword());
        final UserDetails userDetails = appUserImplementation.loadUserByUsername(authRequest.getIdentifier());
        final String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }

    @SneakyThrows
    @Override
    public AppUserResponse register(AppUserRequest appUserRequest) {
        appUserRequest.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
        AppUser user = appUserRepository.register(appUserRequest);
        Random rnd = new Random();
        int otp = rnd.nextInt(999999);
        emailSenderServiceImplementation.sendEmail(appUserRequest.getEmail(), otp);
        return appUserMapper.toResponse(user);
    }

    @Override
    public void verify(String optCode) {

    }

    @SneakyThrows
    @Override
    public void resend(String email) {

    }
}
