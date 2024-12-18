package com.coursework.sushibarbackend;

import com.coursework.sushibarbackend.user.controller.UserController;
import com.coursework.sushibarbackend.user.model.dto.ApiResponse;
import com.coursework.sushibarbackend.user.model.dto.UserUpdateDTO;
import com.coursework.sushibarbackend.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testChangeUserDataSuccess() throws Exception {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setFirstname("John");
        userUpdateDTO.setLastname("Doe");

        ApiResponse expectedResponse = new ApiResponse(true, "User data updated successfully"){};

        when(userService.changeUserData(userUpdateDTO)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ApiResponse> response = userController.changeUserData(userUpdateDTO);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("User data updated successfully", response.getBody().getMessage());

        verify(userService, times(1)).changeUserData(userUpdateDTO);
    }

    @Test
    void testChangeUserDataFailure() throws Exception {
        // Arrange
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setFirstname("John");
        userUpdateDTO.setLastname("Doe");

        when(userService.changeUserData(userUpdateDTO)).thenThrow(new Exception("Failed to update user data"));

        // Act
        Exception exception = assertThrows(Exception.class, () -> userController.changeUserData(userUpdateDTO));

        // Assert
        assertNotNull(exception);
        assertEquals("Failed to update user data", exception.getMessage());

        verify(userService, times(1)).changeUserData(userUpdateDTO);
    }
}

