package com.example.visionarycrofting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "role")
public class Role implements Serializable {
    @Id
    @Column(unique = true)
    private String name;
    private String description;
    @OneToMany(mappedBy = "role")
    private Set<UsersRoles> roles;

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public Set<UsersRoles> getRoles() {
        return roles;
    }

    @JsonSetter
    public void setRoles(Set<UsersRoles> usersRoles) {
        this.roles = usersRoles;
    }

    @Override
    public String toString() {
        return "Role{" +
                ", role='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
