package com.coursework.sushibarbackend.user.controller;

import com.coursework.sushibarbackend.exception.CustomExceptions.AuthenticationFailureException;
import com.coursework.sushibarbackend.user.model.dto.SignUpDTO;
import com.coursework.sushibarbackend.user.service.ApiService;
import com.coursework.sushibarbackend.user.service.AuthService;
import com.coursework.sushibarbackend.user.service.UserService;
import com.coursework.sushibarbackend.util.JwtUtil;
import com.coursework.sushibarbackend.vk.model.dto.ApiResponse;
import com.coursework.sushibarbackend.vk.model.dto.SignInDTO;
import com.coursework.sushibarbackend.vk.model.dto.SilentAuthDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ApiService apiService;

    @PostMapping("/exchangeSilentAuthToken")
    public CompletableFuture<ResponseEntity<ApiResponse>> exchangeSilentAuthToken(@RequestBody SilentAuthDTO silentAuthDTO) {
        return authService.exchangeAndRetrieveProfile(silentAuthDTO.getSilentToken(), silentAuthDTO.getUuid())
                .thenApply(body -> ResponseEntity.ok().body(body))
                .exceptionally(e -> {
                    Throwable cause = e.getCause();
                    if (cause instanceof AuthenticationFailureException) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, e.getMessage()) {
                        });
                    }
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getMessage()) {
                    });
                });
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody SignUpDTO signUpDTO) {
        userService.registerUser(signUpDTO);
        return ResponseEntity.ok(new ApiResponse(true, "Регистрация прошла успешно!") {
        });
    }

    @PostMapping("/authenticate")
    public CompletableFuture<ResponseEntity<ApiResponse>> authenticate(@RequestBody SignInDTO request) {
        return authService.authenticateUser(request)
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> {
                    Throwable cause = e.getCause();
                    if (cause instanceof AuthenticationFailureException) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, cause.getMessage()) {
                        });
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, cause.getMessage()) {
                        });
                    }
                });
    }

}

