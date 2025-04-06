package com.example.gamified_habit_tracker_api.controller;

import com.example.gamified_habit_tracker_api.base.APIResponse;
import com.example.gamified_habit_tracker_api.base.BaseController;
import com.example.gamified_habit_tracker_api.jwt.JwtService;
import com.example.gamified_habit_tracker_api.model.request.AppUserRequest;
import com.example.gamified_habit_tracker_api.model.request.AuthRequest;
import com.example.gamified_habit_tracker_api.model.response.AuthResponse;
import com.example.gamified_habit_tracker_api.service.AppUserService;
import com.example.gamified_habit_tracker_api.service.implementation.AuthServiceImplementation;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/verify")
    public ResponseEntity<APIResponse> verify(@RequestParam String email, @RequestParam @Positive(message = "Otp code cannot be negative or zero") String otpCode) {
        authServiceImplementation.verify(email, otpCode);
        return response(APIResponse.builder()
                .success(true)
                .message("Verified successfully!!!")
                .status(HttpStatus.OK)
                .build());
    }


    @PostMapping("/resend")
    public ResponseEntity<APIResponse> resend(@RequestParam String email) {
        authServiceImplementation.resend(email);
        return response(APIResponse.builder()
                .success(true)
                .message("OTP has successfully resent")
                .build());
    }
}
