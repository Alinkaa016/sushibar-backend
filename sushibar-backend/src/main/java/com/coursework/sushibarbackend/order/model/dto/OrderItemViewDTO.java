package com.coursework.sushibarbackend.order.model.dto;

import com.coursework.sushibarbackend.order.model.Database.OrderItem;
import com.coursework.sushibarbackend.product.model.dto.ProductNestedDTO;

public class OrderItemViewDTO {
    private int id;
    private ProductNestedDTO product;
    private int quantity;

    public OrderItemViewDTO(){

    }

    public OrderItemViewDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.product = new ProductNestedDTO(orderItem.getProduct());
        this.quantity = orderItem.getQuantity();
    }

    public int getId() {
        return id;
    }

    public ProductNestedDTO getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
