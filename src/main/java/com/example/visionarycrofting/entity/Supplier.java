package com.example.visionarycrofting.entity;

import com.example.visionarycrofting.config.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "supplier")
@PrimaryKeyJoinColumn(name = "username")
public class Supplier extends User implements Serializable {
    @OneToMany(mappedBy = "supplier")
    private List<AppelOffre> appelOffres;

    private String name;
    public Supplier() {
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

    @Override
    public String toString() {
        return super.toString();
    }
}
