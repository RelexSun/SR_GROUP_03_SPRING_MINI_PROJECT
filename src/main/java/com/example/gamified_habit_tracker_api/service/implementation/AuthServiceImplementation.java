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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
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
    private final RedisTemplate redisTemplate;

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
        AppUser appUser = authRequest.getIdentifier().contains("@") ?
                appUserRepository.getUserByEmail(authRequest.getIdentifier())
                : appUserRepository.getUserByUsername(authRequest.getIdentifier());
        if(appUser == null) throw new BadRequestException("User is not registered");
        if(!appUser.getIsVerified()) throw new BadRequestException("User needs to verify before login");

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
        String otp  = Integer.toString(rnd.nextInt(999999));
        String existingOTP = (String) redisTemplate.opsForValue().get(otp);
        if (existingOTP != null) {
            throw new BadRequestException("OTP already in use");
        }
        emailSenderServiceImplementation.sendEmail(appUserRequest.getEmail(), otp);
        redisTemplate.opsForValue().set(appUserRequest.getEmail(), otp, Duration.ofMinutes(2));
        return appUserMapper.toResponse(user);
    }

    @Override
    public void verify(String email, String optCode) {
        AppUser appUser = appUserRepository.getUserByEmail(email);
        if (appUser == null) throw new NotFoundException("User doesn't exist");
        if(appUser.getIsVerified()) throw new BadRequestException("User already verified");

        String storedOTP = (String) redisTemplate.opsForValue().get(optCode);
        if(storedOTP == null) throw new BadRequestException("OTP already expired");
        if (!storedOTP.equals(optCode)) throw new BadRequestException("OTP code doesn't match");

        redisTemplate.delete(optCode);
        appUserRepository.updateVerificationStatus(email);
    }

    @SneakyThrows
    @Override
    public void resend(String email) {
        AppUser appUser = appUserRepository.getUserByEmail(email);
        if (appUser == null) throw new NotFoundException("User doesn't exist");
        if(appUser.getIsVerified()) throw new BadRequestException("User already verified");
        Random rnd = new Random();
        String otp  = Integer.toString(rnd.nextInt(999999));
        emailSenderServiceImplementation.sendEmail(appUser.getEmail(), otp);
        redisTemplate.opsForValue().set(appUser.getEmail(), otp, Duration.ofMinutes(2));

    }
}
