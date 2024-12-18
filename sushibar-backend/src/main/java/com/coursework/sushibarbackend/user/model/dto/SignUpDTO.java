package com.coursework.sushibarbackend.user.model.dto;


import com.coursework.sushibarbackend.user.model.entity.User;

public class SignUpDTO {
    private int vkId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String username;
    private String password;
    private String email;

    public SignUpDTO(){

    }

    public SignUpDTO(User user) {
        this.vkId = user.getVkId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.patronymic = user.getPatronymic();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

    public int getVkId() {
        return vkId;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setVkId(int vkId) {
        this.vkId = vkId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

