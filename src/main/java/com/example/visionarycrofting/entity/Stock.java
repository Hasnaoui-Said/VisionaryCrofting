package com.example.visionarycrofting.entity;

import com.example.visionarycrofting.config.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "stock")
@PrimaryKeyJoinColumn(name = "username")
public class Stock extends User implements Serializable {

    @OneToMany(mappedBy = "stock")
    private List<Product> products;

    @OneToMany(mappedBy = "stock")
    private List<AppelOffre> appelOffres;

    private String name;
    private String address;

    public Stock() {
    }

    @JsonIgnore
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @JsonIgnore
    public List<AppelOffre> getAppelOffres() {
        return appelOffres;
    }

    public void setAppelOffres(List<AppelOffre> appelOffres) {
        this.appelOffres = appelOffres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
