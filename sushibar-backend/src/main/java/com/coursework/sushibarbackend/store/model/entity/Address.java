package com.coursework.sushibarbackend.store.model.entity;

import com.coursework.sushibarbackend.store.model.dto.AddressNestedDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "street")
    @NotNull
    private String street;

    @Column(name = "houseNumber")
    @NotNull
    private int houseNumber;

    @Column(name = "postalCode")
    @NotNull
    private int postalCode;

    @Column(name = "apartment")
    @NotNull
    private int apartment;

    @Column(name = "latitude")
    @NotNull
    private String latitude ;

    @Column(name = "longitude")
    @NotNull
    private String longitude ;

    public Address(){

    }

    public Address(AddressNestedDTO address) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.apartment = apartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
