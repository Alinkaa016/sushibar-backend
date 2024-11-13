package com.coursework.sushibarbackend.vk.model.dto;

public class VkUserPartialDTO extends ApiResponse {
    private int vkId;
    private String firstName;
    private String lastName;

    public VkUserPartialDTO(int vkId, String firstName, String lastName, boolean success, String message) {
        super(success, message);
        this.vkId = vkId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getVkId() {
        return vkId;
    }

    public void setVkId(int vkId) {
        this.vkId = vkId;
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
