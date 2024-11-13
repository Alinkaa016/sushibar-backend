package com.coursework.sushibarbackend.order.model.dto;

import com.coursework.sushibarbackend.order.model.Database.ShippingMethod;

public class ShippingMethodCompositeDTO {
    private int id;
    private String description;

    public ShippingMethodCompositeDTO() {

    }

    public ShippingMethodCompositeDTO(ShippingMethod shippingMethod) {
        this.id = shippingMethod.getId();
        this.description = shippingMethod.getDescription();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
