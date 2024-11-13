package com.coursework.sushibarbackend.product.model.dto;


import com.coursework.sushibarbackend.product.model.entity.Product;
import com.coursework.sushibarbackend.store.model.dto.StoreItemNestedDTO;

import java.util.List;

public class ProductViewDTO {
    private int id;
    private String name;
    private double price;
    private String description;
    private float rating;
    private byte[] image;
    private CategoryCompositeDTO category;
    private List<StoreItemNestedDTO> storeList;
    private List<AttributeNestedDTO> attributeList;
    private List<ReviewDTO> reviewList;

    public ProductViewDTO() {
    }

    public ProductViewDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.rating = product.getRating();
        this.image = product.getImage();
        this.storeList = product.getStoreList().stream().map(StoreItemNestedDTO::new).toList();
        this.category = new CategoryCompositeDTO(product.getCategory());
        this.attributeList = product.getProductAttributes().stream().map(AttributeNestedDTO::new).toList();
        this.reviewList = product.getReviewList().stream().map(ReviewDTO::new).toList();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
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

    public List<StoreItemNestedDTO> getStoreList() {
        return storeList;
    }

    public CategoryCompositeDTO getCategory() {
        return category;
    }

    public List<AttributeNestedDTO> getAttributeList() {
        return attributeList;
    }

    public List<ReviewDTO> getReviewList() {
        return reviewList;
    }
}
