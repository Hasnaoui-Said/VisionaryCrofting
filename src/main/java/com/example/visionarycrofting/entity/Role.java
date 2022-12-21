package com.example.visionarycrofting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "role")
public class Role implements Serializable {
    @Id
    private String role;
    private String description;
    @OneToMany(mappedBy = "role")
    private Set<UsersRoles> usersRoles;

    public Role() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public Set<UsersRoles> getUsersRoles() {
        return usersRoles;
    }

    @JsonSetter
    public void setUsersRoles(Set<UsersRoles> usersRoles) {
        this.usersRoles = usersRoles;
    }

    @Override
    public String toString() {
        return "Role{" +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
