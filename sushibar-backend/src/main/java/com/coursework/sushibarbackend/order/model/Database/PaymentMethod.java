package com.coursework.sushibarbackend.order.model.Database;

import com.coursework.sushibarbackend.order.model.dto.PaymentMethodCompositeDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "paymentMethods")
public class PaymentMethod {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    @NotNull
    private String description;

    public PaymentMethod(){

    }

    public PaymentMethod(PaymentMethodCompositeDTO paymentMethodNestedDTO) {
        this.id = paymentMethodNestedDTO.getId();
        this.description = paymentMethodNestedDTO.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
