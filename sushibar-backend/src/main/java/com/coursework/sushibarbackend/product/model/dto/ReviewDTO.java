package com.coursework.sushibarbackend.product.model.dto;


import com.coursework.sushibarbackend.product.model.entity.Review;
import com.coursework.sushibarbackend.user.model.dto.UserNestedDTO;

public class ReviewDTO {
    private int id;
    private UserNestedDTO user;
    private String header;
    private String content;
    private String createdAt;
    private String updatedAt;
    private float rating;

    public ReviewDTO() {
    }

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.user = new UserNestedDTO(review.getUser());
        this.header = review.getHeader();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt().toString();
        this.updatedAt = review.getUpdatedAt() != null ? review.getUpdatedAt().toString() : null;
        this.rating = review.getRating();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserNestedDTO getUser() {
        return user;
    }

    public void setUser(UserNestedDTO user) {
        this.user = user;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
