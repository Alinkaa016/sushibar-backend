package com.coursework.sushibarbackend.user.model.dto;

import com.coursework.sushibarbackend.user.model.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserViewDTO {
    private String firstName;
    private String lastName;
    private String patronymic;
    private boolean isTwoFactorEnabled;
    private float deposit;
    @JsonProperty("isChildModeEnabled")
    private boolean isChildModeEnabled;
    private boolean areNotificationsEnabled;

    public UserViewDTO(){

    }

    public UserViewDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.patronymic = user.getPatronymic();
        this.deposit = user.getDeposit();
        this.isChildModeEnabled = user.isChildModeEnabled();
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public float getDeposit() {
        return deposit;
    }

    public boolean isTwoFactorEnabled() {
        return isTwoFactorEnabled;
    }

    public boolean isChildModeEnabled() {
        return isChildModeEnabled;
    }

    public boolean isAreNotificationsEnabled() {
        return areNotificationsEnabled;
    }
}
