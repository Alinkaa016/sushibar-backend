package com.coursework.sushibarbackend.product.model.dto;

import com.onlineshop.onlineshop.OLD.Models.Database.Product.Product;

public class ProductNestedDTO {
    private int id;
    private String name;
    private String description;
    private float rating;
    private byte[] image;

    public ProductNestedDTO() {
    }

    public ProductNestedDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.rating = product.getRating();
        this.image = product.getImage();
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

    public float getRating() {
        return rating;
    }

    public byte[] getImage() {
        return image;
    }
}
