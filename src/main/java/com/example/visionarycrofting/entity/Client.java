package com.example.visionarycrofting.entity;

import com.example.visionarycrofting.config.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "username")
public class Client extends User implements Serializable {
    @OneToMany(mappedBy = "client")
    private List<Command> commands;

    public Client() {
    }


    @JsonIgnore
    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
