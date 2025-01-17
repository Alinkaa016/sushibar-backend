package com.coursework.sushibarbackend.product.model.dto;


import com.coursework.sushibarbackend.product.model.entity.Category;

public class CategoryCompositeDTO {
    private int id;
    private String name;
    private String description;

    public CategoryCompositeDTO(){

    }

    public CategoryCompositeDTO(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
