package com.coursework.sushibarbackend.product.model.dto;

import com.coursework.sushibarbackend.product.model.entity.ProductAttribute;

public class AttributeNestedDTO {
    private int id;
    private String name;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AttributeNestedDTO(ProductAttribute productAttribute) {
        this.id = productAttribute.getAttribute().getId();
        this.name = productAttribute.getAttribute().getName();
        this.value = productAttribute.getValue();
    }

    public AttributeNestedDTO() {
    }
}
