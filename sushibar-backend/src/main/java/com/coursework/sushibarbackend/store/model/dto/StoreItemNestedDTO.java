package com.coursework.sushibarbackend.store.model.dto;


import com.coursework.sushibarbackend.store.model.entity.StoreItem;

public class StoreItemNestedDTO {
    private int id;
    private int quantity;
    private StoreNestedDTO storeNestedDTO;

    public StoreItemNestedDTO(){

    }

    public StoreItemNestedDTO(StoreItem storeItem) {
        this.id = storeItem.getId();
        this.quantity = storeItem.getQuantity();
        this.storeNestedDTO = new StoreNestedDTO(storeItem.getStore());
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public StoreNestedDTO getStoreNestedDTO() {
        return storeNestedDTO;
    }
}
