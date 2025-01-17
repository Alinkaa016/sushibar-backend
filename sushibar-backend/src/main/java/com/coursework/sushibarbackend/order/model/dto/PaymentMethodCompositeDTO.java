package com.coursework.sushibarbackend.order.model.dto;


import com.coursework.sushibarbackend.order.model.Database.PaymentMethod;

public class PaymentMethodCompositeDTO {
    private int id;
    private String description;

    public PaymentMethodCompositeDTO() {

    }

    public PaymentMethodCompositeDTO(PaymentMethod paymentMethod) {
        this.id = paymentMethod.getId();
        this.description = paymentMethod.getDescription();
    }

    public PaymentMethodCompositeDTO(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
