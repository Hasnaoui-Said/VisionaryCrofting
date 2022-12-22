package com.example.visionarycrofting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_roles")
public class UsersRoles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User username;

    @ManyToOne
    private Role role;

    public UsersRoles() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @JsonIgnore
    public Role getRole() {
        return role;
    }

    @JsonSetter
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UsersRoles{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
