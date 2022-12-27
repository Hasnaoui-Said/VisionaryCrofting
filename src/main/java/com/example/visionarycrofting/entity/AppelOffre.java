package com.example.visionarycrofting.entity;

import com.example.visionarycrofting.enumeration.StatusAppel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "appelOffre")
public class AppelOffre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotEmpty
    private String ref;
    @Enumerated(value = EnumType.STRING)
    private StatusAppel status;
    @NotNull @NotEmpty @Size(min = 1)
    private Integer quantity;
    @NotNull @NotEmpty
    private String refProduct;
    @ManyToOne
    @NotNull @NotEmpty
    private Stock stock;
    @ManyToOne
    private Supplier supplier;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier Supplier) {
        this.supplier = Supplier;
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
