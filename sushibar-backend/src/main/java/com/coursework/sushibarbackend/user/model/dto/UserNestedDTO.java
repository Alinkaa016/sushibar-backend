package com.coursework.sushibarbackend.user.model.dto;


import com.coursework.sushibarbackend.user.model.entity.User;

public class UserNestedDTO {
    private String username;
    private String firstName;
    private String lastName;

    public UserNestedDTO(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public UserNestedDTO(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
