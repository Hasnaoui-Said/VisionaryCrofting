package com.example.visionarycrofting.repository;

import com.example.visionarycrofting.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao extends JpaRepository<Client, Long> {
    int deleteByEmail(String email);
    Client findByEmail(String email);
    boolean existsByEmail(String s);
}
