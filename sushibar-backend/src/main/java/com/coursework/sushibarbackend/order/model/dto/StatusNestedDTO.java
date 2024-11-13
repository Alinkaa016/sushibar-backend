package com.coursework.sushibarbackend.order.model.dto;

import com.coursework.sushibarbackend.order.model.Database.Status;

public class StatusNestedDTO {
    private int id;
    private String description;

    public StatusNestedDTO() {

    }

    public StatusNestedDTO(Status status) {
        this.id = status.getId();
        this.description = status.getDescription();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
