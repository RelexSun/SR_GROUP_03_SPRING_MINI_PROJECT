package com.example.gamified_habit_tracker_api.controller;

import com.example.gamified_habit_tracker_api.base.APIResponse;
import com.example.gamified_habit_tracker_api.base.BaseController;
import com.example.gamified_habit_tracker_api.jwt.JwtService;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequest;
import com.example.gamified_habit_tracker_api.model.request.AuthRequest;
import com.example.gamified_habit_tracker_api.model.response.AuthResponse;
import com.example.gamified_habit_tracker_api.service.AppUserService;
import com.example.gamified_habit_tracker_api.service.implementation.AuthServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthServiceImplementation authServiceImplementation;

//    private void authenticate(String email, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody AuthRequest request) throws Exception {
        return response(APIResponse.builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Login successfully")
                .payload( authServiceImplementation.login(request))
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@RequestBody AppUserRequest request){
        return response(APIResponse.builder()
                .success(true)
                .message("Register successfully")
                .status(HttpStatus.CREATED)
                .payload(authServiceImplementation.register(request))
                .build());
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) throws Exception {
//        AuthResponse authResponse = authService.login(authRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@Valid @RequestBody AppUserRequest appUserRequest) throws MessagingException {
//        AppUserResponse appUserResponse = authService.register(appUserRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(appUserResponse);
//    }
//
//    @PutMapping("/verify")
//    public ResponseEntity<?> verify(@RequestParam @Positive(message = "Otp code cannot be negative or zero") String otpCode) {
//        authService.verify(otpCode);
//        return ResponseEntity.status(HttpStatus.OK).body("Your account is verify successful");
//    }
//
//    @PostMapping("/resend")
//    public ResponseEntity<?> resend(@RequestParam String email) throws MessagingException {
//        authService.resend(email);
//        return ResponseEntity.status(HttpStatus.OK).body("Resend otp code successful");
//    }
}
