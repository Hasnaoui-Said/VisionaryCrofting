package org.vc.visionarycroftingmvc.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.vc.visionarycroftingmvc.enumeration.Category;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    private String name;
    private double prix;
    private String description;
    private int quantity;
    @ManyToOne
    private Stock stock;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<CommandItem> commandItems;

    public Product() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonIgnore
    public List<CommandItem> getCommandItems() {
        return commandItems;
    }

    public void setCommandItems(List<CommandItem> commandItems) {
        this.commandItems = commandItems;
    }


    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", name='" + name + '\'' +
                ", prix='" + prix + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", stock=" + stock +
                ", category=" + category +
                ", stock=" + stock +
                '}';
    }
}
