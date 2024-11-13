package com.coursework.sushibarbackend.shoppingCart.model.dto;


import com.coursework.sushibarbackend.product.model.dto.ProductViewDTO;
import com.coursework.sushibarbackend.shoppingCart.model.entity.CartItem;

public class CartItemViewDTO {
    private ProductViewDTO product;
        private int quantity;

    public CartItemViewDTO(){

    }

    public CartItemViewDTO(CartItem cartItem) {
        this.product = new ProductViewDTO(cartItem.getProduct());
                this.quantity = cartItem.getQuantity();
    }


    public ProductViewDTO getProduct() {
        return product;
    }

    public void setProduct(ProductViewDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
