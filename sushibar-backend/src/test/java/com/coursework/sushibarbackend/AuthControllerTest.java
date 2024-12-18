package com.coursework.sushibarbackend;

import com.coursework.sushibarbackend.user.controller.AuthController;
import com.coursework.sushibarbackend.user.model.dto.ApiResponse;
import com.coursework.sushibarbackend.user.model.dto.SignInDTO;
import com.coursework.sushibarbackend.user.model.dto.SignUpDTO;
import com.coursework.sushibarbackend.user.service.AuthService;
import com.coursework.sushibarbackend.user.service.UserService;
import com.coursework.sushibarbackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.CompletableFuture;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void testRegisterUser() throws Exception {
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setUsername("testUser");
        signUpDTO.setPassword("password123");

        ApiResponse response = new ApiResponse(true, "Регистрация прошла успешно!"){};

        Mockito.doNothing().when(userService).registerUser(Mockito.any(SignUpDTO.class));

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(signUpDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Регистрация прошла успешно!"));
    }

    @Test
    void testAuthenticateUser() throws Exception {
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setUsername("testUser");
        signInDTO.setPassword("password123");

        ApiResponse response = new ApiResponse(true, "Авторизация успешна!"){};

        Mockito.when(authService.authenticateUser(Mockito.any(SignInDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(response));

        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(signInDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Авторизация успешна!"));
    }
}

