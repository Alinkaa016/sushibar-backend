package com.coursework.sushibarbackend.user.model.entity;

import com.coursework.sushibarbackend.order.model.Database.Order;
import com.coursework.sushibarbackend.shoppingCart.model.entity.ShoppingCart;
import com.coursework.sushibarbackend.user.model.dto.SignUpDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vk_id", unique = true)
    private Integer vkId;

    @Column(name = "firstname")
    @NotNull
    private String firstName;

    @Column(name = "lastname")
    @NotNull
    private String lastName;

    @Column(name = "patronymic")
    @NotNull
    private String patronymic;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "deposit")
    @NotNull
    private float deposit = 0.f;

    @Column(name = "isChildModeEnabled")
    @NotNull
    private boolean isChildModeEnabled = false;

    @JoinColumn(name = "cart_id")
    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart = new ShoppingCart();

    @JoinColumn(name = "user_id")
    @OneToMany()
    private List<Order> orderList = new ArrayList<>();


    public User(){

    }


    public User(SignUpDTO signUpDTO) {
        this.firstName = signUpDTO.getFirstName();
        this.lastName = signUpDTO.getLastName();
        this.patronymic = signUpDTO.getPatronymic();
        this.username = signUpDTO.getUsername();
        this.password = signUpDTO.getPassword();
        this.email = signUpDTO.getEmail();
    }

    public void update(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.patronymic = user.getPatronymic();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.deposit = user.getDeposit();
        this.isChildModeEnabled = user.isChildModeEnabled();
        this.shoppingCart = user.getShoppingCart();
        this.orderList = user.getOrderList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getVkId() {
        return vkId;
    }

    public void setVkId(Integer vkId) {
        this.vkId = vkId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public boolean isChildModeEnabled() {
        return isChildModeEnabled;
    }

    public void setChildModeEnabled(boolean childModeEnabled) {
        isChildModeEnabled = childModeEnabled;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void addToOrderList(Order order) {
        this.orderList.add(order);
    }

    public void removeFromToOrderList(Order order) {
        this.orderList.removeIf(item -> item.getId() == order.getId());
    }
}