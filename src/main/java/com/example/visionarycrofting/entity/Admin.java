package com.example.visionarycrofting.entity;

import com.example.visionarycrofting.config.security.entity.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "supplier")
@PrimaryKeyJoinColumn(name = "username")
public class Admin extends User implements Serializable {

    public Admin() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
