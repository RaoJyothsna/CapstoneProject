package com.upgrad.eshop.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ESHOP_PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable=false,unique=true)
    private String Name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String Description;
    @Column(nullable = false)
    private String Manufacturer;
    @Column(nullable = false)
    private String image_url;
    @Column(nullable = false)
    private int availItems;
    @Column(nullable = false)
    private Date created;
    @Column(nullable = false)
    private Date updated;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getAvailItems() {
        return availItems;
    }

    public void setAvailItems(int availItems) {
        this.availItems = availItems;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Product(){}

    public Product(String name, String category, String description, String Manufacturer,String img,int avail) {
        this.availItems=avail;
        this.category=category;
        this.Name=name;
        this.Manufacturer=Manufacturer;
        this.Description=description;
        this.image_url=img;
    }
}
