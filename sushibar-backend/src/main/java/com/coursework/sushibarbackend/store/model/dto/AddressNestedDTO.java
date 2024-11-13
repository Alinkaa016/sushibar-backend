package com.coursework.sushibarbackend.store.model.dto;


import com.coursework.sushibarbackend.store.model.entity.Address;

public class AddressNestedDTO {
    private int id;
    private String city;
    private String street;
    private int houseNumber;
    private int postalCode;
    private int apartment;
    private String latitude;
    private String longitude;

    public AddressNestedDTO(){  

    }

    public AddressNestedDTO(Address address) {
        this.id = address.getId();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
        this.postalCode = address.getPostalCode();
        this.apartment = address.getApartment();
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public int getApartment() {
        return apartment;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
