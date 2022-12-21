package com.example.visionarycrofting.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private UUID uuid;
    @Id
    @Size(min = 4, max = 20)
    private String username;

    private String email;
    private String password;
    private String phone;
    private boolean active;
    @OneToMany(mappedBy = "username")
    private List<UsersRoles> usersRoles;
    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", uuid='" + uuid + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", active='" + active + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
