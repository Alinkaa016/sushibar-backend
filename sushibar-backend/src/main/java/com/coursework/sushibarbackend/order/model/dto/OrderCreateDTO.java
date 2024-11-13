package com.coursework.sushibarbackend.order.model.dto;


import com.coursework.sushibarbackend.store.model.dto.StoreNestedDTO;

import java.util.List;

public class OrderCreateDTO {
    private float totalAmount;
    private String deliveryAddress;
        private PaymentMethodCompositeDTO paymentMethod;
    private ShippingMethodCompositeDTO shippingMethod;
    private List<OrderItemCreateDTO> orderItems;
    private StoreNestedDTO store;

    public OrderCreateDTO() {
    }

    public float getTotalAmount() {
        return totalAmount;
    }


    public PaymentMethodCompositeDTO getPaymentMethod() {
        return paymentMethod;
    }

    public ShippingMethodCompositeDTO getShippingMethod() {
        return shippingMethod;
    }

    public List<OrderItemCreateDTO> getOrderItems() {
        return orderItems;
    }

    public StoreNestedDTO getStore() {
        return store;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
}