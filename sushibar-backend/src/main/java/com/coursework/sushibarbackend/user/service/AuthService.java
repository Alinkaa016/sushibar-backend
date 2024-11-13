package com.coursework.sushibarbackend.user.service;

import com.coursework.sushibarbackend.exception.CustomExceptions.AuthenticationFailureException;
import com.coursework.sushibarbackend.user.model.dto.ApiResponse;
import com.coursework.sushibarbackend.user.model.dto.SignInDTO;
import com.coursework.sushibarbackend.user.model.dto.UserTokenDTO;
import com.coursework.sushibarbackend.user.model.entity.User;
import com.coursework.sushibarbackend.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Component
@Service
@Transactional
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Async("taskExecutor")
    public CompletableFuture<ApiResponse> authenticateUser(SignInDTO request) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                UserDetails userDetails;
                User user;
                boolean isVk = false;
                try{
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                } catch (Exception e){
                    throw new AuthenticationFailureException("Неверный логин или пароль");
                }
                user = userService.getByUsername(request.getUsername());

                userDetails = userService.loadUserByUsername(user.getUsername());
                return new UserTokenDTO(jwtUtil.generateToken(userDetails), user.getUsername(), true, "token", user.isChildModeEnabled(), isVk);
            } catch (Exception e) {
                throw new CompletionException(new AuthenticationFailureException(e.getMessage()));
            }
        });
    }
}
