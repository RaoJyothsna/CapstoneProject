package com.upgrad.eshop.dtos;

import com.upgrad.eshop.services.ShippingService;

public class ShippingResponse {

    private int id;
    private String Name;
    private String Phone;
    private String Street;
    private String Landmark;
    private String City;
    private String State;
    private String zipcode;

    ShippingResponse(String name,String phone,String street,String landmark,String city,String state,String zipcode)
    {
        this.City=city;
        this.Landmark=landmark;
        this.Name=name;
        this.Phone=phone;
        this.State=state;
        this.Street=street;
        this.zipcode=zipcode;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getLandmark() {
        return Landmark;
    }

    public void setLandmark(String landmark) {
        Landmark = landmark;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
