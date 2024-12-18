package com.coursework.sushibarbackend.order.model.dto;

import com.coursework.sushibarbackend.order.model.Database.Order;
import com.coursework.sushibarbackend.order.model.Database.PaymentMethod;
import com.coursework.sushibarbackend.order.model.Database.ShippingMethod;
import com.coursework.sushibarbackend.order.model.Database.Status;
import com.coursework.sushibarbackend.store.model.dto.StoreNestedDTO;
import com.coursework.sushibarbackend.user.service.AuthService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderViewDTO {
    private int id;
    private float totalAmount;
    private String creationDate;
    private String completionDate;
    private String deliveryAddress;
    private Status status;
    private PaymentMethod paymentMethod;
    private ShippingMethod shippingMethod;
    private List<OrderItemViewDTO> orderItems;
    private StoreNestedDTO store;
    @JsonIgnore
    private static final Logger logger = LoggerFactory.getLogger(OrderViewDTO.class);


    public OrderViewDTO() {
    }

    public OrderViewDTO(Order order) {
        this.id = order.getId();
        this.totalAmount = order.getTotalAmount();
        this.creationDate = order.getCreationDate().toString();
        this.deliveryAddress = order.getDeliveryAddress();
        this.completionDate = order.getCompletionDate() != null ? order.getCompletionDate().toString() : null;
        this.status = order.getStatus();
        this.paymentMethod = order.getPaymentMethod();
        this.shippingMethod = order.getShippingMethod();
        this.orderItems = order.getOrderItems().stream().map(OrderItemViewDTO::new).toList();
        this.store = new StoreNestedDTO(order.getStore());
    }

    public int getId() {
        return id;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public Status getStatus() {
        return status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public List<OrderItemViewDTO> getOrderItems() {
        return orderItems;
    }

    public StoreNestedDTO getStore() {
        return store;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public void setOrderItems(List<OrderItemViewDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public void setStore(StoreNestedDTO store) {
        this.store = store;
    }
}
