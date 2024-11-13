package com.coursework.sushibarbackend.user.controller;

import com.coursework.sushibarbackend.user.model.dto.UpdateSettingsDTO;
import com.coursework.sushibarbackend.user.model.dto.UserUpdateDTO;
import com.coursework.sushibarbackend.user.model.dto.UserViewDTO;
import com.coursework.sushibarbackend.user.model.entity.User;
import com.coursework.sushibarbackend.user.service.UserService;
import com.coursework.sushibarbackend.vk.model.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/changeUserData")
    public ResponseEntity<ApiResponse> changeUserData(@RequestBody UserUpdateDTO userUpdateDTO) throws Exception {
        return ResponseEntity.ok(userService.changeUserData(userUpdateDTO));
    }

    @PostMapping("/settingChildMode")
    public ResponseEntity<ApiResponse> settingChildMode(@RequestBody UpdateSettingsDTO updateSettingsDTO) throws Exception {
        return ResponseEntity.ok(userService.settingChildMode(updateSettingsDTO));
    }

    @GetMapping(path = "/resetUser")
    public void test() throws Exception {
        List<User> users = userService.getAll();
        for (User user : users){
            if (user.getVkId() != 610502189){
                user.setVkId(0);
                userService.update(user);
            }
        }
    }

    @PostMapping(path = "/topUpDeposit")
    public ApiResponse topUpDeposit(@RequestParam int amount) throws Exception {
        return userService.topUpDeposit(amount);
    }

    @GetMapping(path = "/deposit")
    public float getDeposit() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByUsername(userDetails.getUsername());
        return user.getDeposit();
    }

    @GetMapping(path = "/getUserInfo")
    public UserViewDTO getUserInfo() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByUsername(userDetails.getUsername());
        return new UserViewDTO(user);
    }
}
