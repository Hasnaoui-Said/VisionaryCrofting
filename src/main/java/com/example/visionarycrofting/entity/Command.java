package com.example.visionarycrofting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name = "command")
public class Command implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotEmpty
    private String ref;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date dateCommand;

    private double prixTotal;
    @ManyToOne
    @NotNull @NotEmpty @Valid
    private Client client;
    @OneToMany(mappedBy = "command")
    @NotNull @NotEmpty @Valid
    private List<CommandItem> commandItems;

    //


    public Command() {
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

    public Date getDateCommand() {
        return dateCommand;
    }

    public void setDateCommand(Date dateCommand) {
        this.dateCommand = dateCommand;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<CommandItem> getCommandItems() {
        return commandItems;
    }

    public void setCommandItems(List<CommandItem> commandItems) {
        this.commandItems = commandItems;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", dateCommand=" + dateCommand +
                ", prixTotal=" + prixTotal +
                ", client=" + client +
                ", commandItems=" + commandItems +
                '}';
    }
}
