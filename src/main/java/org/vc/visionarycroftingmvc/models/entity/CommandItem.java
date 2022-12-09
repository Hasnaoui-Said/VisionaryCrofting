package org.vc.visionarycroftingmvc.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "commandItem")
public class CommandItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    private double prix;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Command command;
    private int quantity;

    //

    public CommandItem() {
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonIgnore
    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "CommandItem{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", prix=" + prix +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

}
