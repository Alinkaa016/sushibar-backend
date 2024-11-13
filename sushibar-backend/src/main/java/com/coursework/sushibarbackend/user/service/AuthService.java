package com.coursework.sushibarbackend.user.service;

import com.coursework.sushibarbackend.exception.CustomExceptions.AuthenticationFailureException;
import com.coursework.sushibarbackend.user.model.dto.UserTokenDTO;
import com.coursework.sushibarbackend.user.model.entity.User;
import com.coursework.sushibarbackend.util.JwtUtil;
import com.coursework.sushibarbackend.vk.model.dto.ApiResponse;
import com.coursework.sushibarbackend.vk.model.dto.SignInDTO;
import com.coursework.sushibarbackend.vk.model.dto.VkUserPartialDTO;
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
public class AuthService{
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ApiService apiService;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Async("taskExecutor")
    public CompletableFuture<ApiResponse> exchangeAndRetrieveProfile(String silentToken, String uuid) {
        return apiService.exchangeSilentAuthToken(silentToken, uuid)
                .thenCompose(vkApiResponse -> {
                    Optional<User> user = userService.getByVkId(vkApiResponse.getResponse().getUserId());
                    if (user.isPresent()) {
                        return authenticateUser(new SignInDTO(vkApiResponse.getResponse().getUserId()));
                    }
                    return apiService.getProfileInfo(vkApiResponse.getResponse().getAccessToken())
                            .thenApply(profileInfo -> new VkUserPartialDTO(
                                    vkApiResponse.getResponse().getUserId(),
                                    profileInfo.getResponse().get(0).getFirstName(),
                                    profileInfo.getResponse().get(0).getLastName(),
                                    true,
                                    "User data"
                            ));
                })
                .exceptionally(e -> {
                    throw new CompletionException(new AuthenticationFailureException(e.getMessage()));
                });
    }

    @Async("taskExecutor")
    public CompletableFuture<ApiResponse> authenticateUser(SignInDTO request) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                UserDetails userDetails;
                User user;
                boolean isVk = false;
                if (request.getVkId() != 0) {
                    Optional<User> findUser = userService.getByVkId(request.getVkId());
                    isVk = true;
                    if (findUser.isEmpty()) throw new UsernameNotFoundException("Пользователь с таким vkId не найден.");
                    else user = findUser.get();
                } else {
                    try{
                        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                    } catch (Exception e){
                        throw new AuthenticationFailureException("Неверный логин или пароль");
                    }
                    user = userService.getByUsername(request.getUsername());
                }
                userDetails = userService.loadUserByUsername(user.getUsername());
                return new UserTokenDTO(jwtUtil.generateToken(userDetails), user.getUsername(), true, "token", user.isChildModeEnabled(), isVk);
            } catch (Exception e) {
                throw new CompletionException(new AuthenticationFailureException(e.getMessage()));
            }
        });
    }
}
