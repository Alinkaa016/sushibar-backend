package com.coursework.sushibarbackend.order.model.Database;

import com.coursework.sushibarbackend.order.model.dto.OrderItemViewDTO;
import com.coursework.sushibarbackend.product.model.entity.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public OrderItem(){
    }

    public OrderItem(OrderItemViewDTO orderItemViewDTO){
        this.id = orderItemViewDTO.getId();
        this.product = new Product(orderItemViewDTO.getProduct());
        this.quantity = orderItemViewDTO.getQuantity();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
