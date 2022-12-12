package org.vc.visionarycroftingmvc.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "stock")
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String phone;
    @OneToMany(mappedBy = "stock")
    private List<Product> products;

    @OneToMany(mappedBy = "stock")
    private List<AppelOffre> appelOffres;


    //


    public Stock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "Stock:{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", address:'" + address + '\'' +
                ", email:'" + email + '\'' +
                ", password:'" + password + '\'' +
                ", phone:'" + phone + '\'' +
                '}';
    }
}
