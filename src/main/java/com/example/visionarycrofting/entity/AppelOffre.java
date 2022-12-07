package com.example.visionarycrofting.entity;

import com.example.visionarycrofting.enumeration.StatusAppel;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "appelOffre")
public class AppelOffre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    @Enumerated(value = EnumType.STRING)
    private StatusAppel status;
    private int quantity;
    private String refProduct;
    @ManyToOne
    private Stock stock;
    @ManyToOne
    private Fournisseur fournisseur;

    //


    public AppelOffre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public StatusAppel getStatus() {
        return status;
    }

    public void setStatus(StatusAppel status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRefProduct() {
        return refProduct;
    }

    public void setProduct(String product) {
        this.refProduct = product;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Fournisseur getFournissour() {
        return fournisseur;
    }

    public void setFournissour(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override
    public String toString() {
        return "AppelOffre{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", status=" + status +
                ", quantity=" + quantity +
                ", product=" + refProduct +
                ", stock=" + stock +
                '}';
    }

    public void setRefProduct(String refProduct) {
        this.refProduct = refProduct;
    }
}
