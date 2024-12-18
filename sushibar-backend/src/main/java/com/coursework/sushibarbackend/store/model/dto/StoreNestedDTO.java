package com.coursework.sushibarbackend.store.model.dto;


import com.coursework.sushibarbackend.store.model.entity.Store;

public class StoreNestedDTO {
    private int id;
    private String name;
    private AddressNestedDTO address;

    public StoreNestedDTO(){

    }

    public StoreNestedDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StoreNestedDTO(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.address = new AddressNestedDTO(store.getAddress());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AddressNestedDTO getAddress() {
        return address;
    }
}
