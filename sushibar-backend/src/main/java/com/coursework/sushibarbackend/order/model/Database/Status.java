package com.coursework.sushibarbackend.order.model.Database;

import com.coursework.sushibarbackend.order.model.dto.StatusNestedDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    @NotNull
    private String description;

    public Status(){

    }

    public Status(StatusNestedDTO statusNestedDTO) {
        this.id = statusNestedDTO.getId();
        this.description = statusNestedDTO.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
