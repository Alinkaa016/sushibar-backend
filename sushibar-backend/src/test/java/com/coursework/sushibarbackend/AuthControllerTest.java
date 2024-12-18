package com.coursework.sushibarbackend;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.mockito.Mockito.*;
//
//import com.coursework.sushibarbackend.user.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import com.coursework.sushibarbackend.user.controller.AuthController;
//import com.coursework.sushibarbackend.user.model.dto.SignInDTO;
//import com.coursework.sushibarbackend.user.model.dto.SignUpDTO;
//import com.coursework.sushibarbackend.user.model.dto.UserTokenDTO;
//import com.coursework.sushibarbackend.user.service.AuthService;
//
//@WebMvcTest(AuthController.class)
//public class AuthControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AuthService authService;
//
//    @MockBean
//    private UserService userService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void testSignInSuccess() throws Exception {
//        SignInDTO signInDTO = new SignInDTO();
//        signInDTO.setUsername("test@example.com");
//        signInDTO.setPassword("password123");
//
//        UserTokenDTO userTokenDTO = new UserTokenDTO();
//        userTokenDTO.setToken("dummyToken");
//
//        when(authService.authenticateUser(any(SignInDTO.class))).thenReturn(userTokenDTO);
//
//        mockMvc.perform(post("/auth/signin")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(signInDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").value("dummyToken"));
//    }
//
//    @Test
//    public void testSignUpSuccess() throws Exception {
//        SignUpDTO signUpDTO = new SignUpDTO();
//        signUpDTO.setEmail("newuser@example.com");
//        signUpDTO.setPassword("password123");
//        signUpDTO.setFirstName("John");
//        signUpDTO.setLastName("Doe");
//
//        when(userService.registerUser(any(SignUpDTO.class))).thenReturn(true);
//
//        mockMvc.perform(post("/auth/signup")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(signUpDTO)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void testSignInInvalidCredentials() throws Exception {
//        SignInDTO signInDTO = new SignInDTO();
//        signInDTO.setUsername("test@example.com");
//        signInDTO.setPassword("wrongpassword");
//
//        when(authService.authenticateUser(any(SignInDTO.class))).thenThrow(new RuntimeException("Invalid credentials"));
//
//        mockMvc.perform(post("/auth/signin")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(signInDTO)))
//                .andExpect(status().isUnauthorized());
//    }
//}

import com.coursework.sushibarbackend.exception.CustomExceptions.AuthenticationFailureException;
import com.coursework.sushibarbackend.user.controller.AuthController;
import com.coursework.sushibarbackend.user.model.dto.ApiResponse;
import com.coursework.sushibarbackend.user.model.dto.SignInDTO;
import com.coursework.sushibarbackend.user.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateSuccess() throws Exception {
        // Arrange
        SignInDTO request = new SignInDTO();
        request.setUsername("testuser");
        request.setPassword("password123");

        ApiResponse expectedResponse = new ApiResponse(true, "Authentication successful"){};
        CompletableFuture<ApiResponse> mockResponse = CompletableFuture.completedFuture(expectedResponse);

        when(authService.authenticateUser(request)).thenReturn(mockResponse);

        // Act
        CompletableFuture<ResponseEntity<ApiResponse>> resultFuture = authController.authenticate(request);
        ResponseEntity<ApiResponse> result = resultFuture.join();

        // Assert
        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(expectedResponse.isSuccess(), result.getBody().isSuccess());
        assertEquals(expectedResponse.getMessage(), result.getBody().getMessage());

        verify(authService, times(1)).authenticateUser(request);
    }

    @Test
    void testAuthenticateFailure() {
        // Arrange
        SignInDTO request = new SignInDTO();
        request.setUsername("testuser");
        request.setPassword("wrongpassword");

        CompletableFuture<ApiResponse> mockResponse = CompletableFuture.failedFuture(
                new AuthenticationFailureException("Invalid credentials")
        );

        when(authService.authenticateUser(request)).thenReturn(mockResponse);

        // Act
        CompletableFuture<ResponseEntity<ApiResponse>> resultFuture = authController.authenticate(request);
        ResponseEntity<ApiResponse> result = resultFuture.join();

        // Assert
        assertNotNull(result);
        assertEquals(400, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertFalse(result.getBody().isSuccess());
        assertEquals("Invalid credentials", result.getBody().getMessage());

        verify(authService, times(1)).authenticateUser(request);
    }

    @Test
    void testAuthenticateServerError() {
        // Arrange
        SignInDTO request = new SignInDTO();
        request.setUsername("testuser");
        request.setPassword("password123");

        CompletableFuture<ApiResponse> mockResponse = CompletableFuture.failedFuture(
                new RuntimeException("Unexpected error")
        );

        when(authService.authenticateUser(request)).thenReturn(mockResponse);

        // Act
        CompletableFuture<ResponseEntity<ApiResponse>> resultFuture = authController.authenticate(request);
        ResponseEntity<ApiResponse> result = resultFuture.join();

        // Assert
        assertNotNull(result);
        assertEquals(500, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertFalse(result.getBody().isSuccess());
        assertEquals("Unexpected error", result.getBody().getMessage());

        verify(authService, times(1)).authenticateUser(request);
    }
}

