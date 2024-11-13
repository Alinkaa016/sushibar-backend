package com.coursework.sushibarbackend.order.model.Database;

import com.coursework.sushibarbackend.order.model.dto.OrderCreateDTO;
import com.coursework.sushibarbackend.order.model.dto.OrderViewDTO;
import com.coursework.sushibarbackend.store.model.entity.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "totalAmount")
    @NotNull
    private float totalAmount;

    @Column(name = "deliveryAddress")
    private String deliveryAddress;

    @Column(name = "creationDate")
    @NotNull
    private LocalDateTime creationDate;

    @Column(name = "completionDate")
    private LocalDateTime completionDate;

    @JoinColumn(name = "status_id")
    @ManyToOne
    private Status status;

    @JoinColumn(name = "payment_method_id")
    @ManyToOne
    private PaymentMethod paymentMethod;

    @JoinColumn(name = "shipping_method_id")
    @ManyToOne
    private ShippingMethod shippingMethod;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Order(){

    }

    public Order(OrderViewDTO orderViewDTO) {
        this.id = orderViewDTO.getId();
        this.totalAmount = orderViewDTO.getTotalAmount();
                        this.status = orderViewDTO.getStatus();
        this.deliveryAddress = orderViewDTO.getDeliveryAddress();
        this.paymentMethod = orderViewDTO.getPaymentMethod();
        this.shippingMethod = orderViewDTO.getShippingMethod();
        this.orderItems = orderViewDTO.getOrderItems().stream().map(OrderItem::new).toList();
        this.store = new Store(orderViewDTO.getStore());
    }

    public Order(OrderCreateDTO orderCreateDTO) {
        this.totalAmount = orderCreateDTO.getTotalAmount();
        this.paymentMethod = new PaymentMethod(orderCreateDTO.getPaymentMethod());
        this.shippingMethod = new ShippingMethod(orderCreateDTO.getShippingMethod());
        this.deliveryAddress = orderCreateDTO.getDeliveryAddress();
            }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addToOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public void removeFromOrderItems(OrderItem orderItem) {
        this.orderItems.removeIf(item -> item.getId() == orderItem.getId());
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
