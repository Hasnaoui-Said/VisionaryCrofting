package com.example.visionarycrofting.config.security.entity;


import com.example.visionarycrofting.entity.UsersRoles;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User implements Serializable {
    private UUID uuid;
    @Id
    @Column(unique = true, name = "username")
    @Size(min = 4, max = 20)
    private String username;

    @Column(unique = true)
    private String email;
    //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phone;

    @CreatedDate
    protected Date createdAt;

    @LastModifiedDate
    protected Date updatedAt;
    private boolean active;
    @OneToMany(mappedBy = "username", fetch = FetchType.EAGER)
    private List<UsersRoles> roles;

    public User() {
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", usersRoles=" + roles +
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isActive() {
        return active;
    }

    public List<UsersRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<UsersRoles> usersRoles) {
        this.roles = usersRoles;
    }
}
