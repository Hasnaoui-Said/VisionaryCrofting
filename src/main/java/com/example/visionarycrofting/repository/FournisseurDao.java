package com.example.visionarycrofting.repository;

import com.example.visionarycrofting.entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurDao extends JpaRepository<Fournisseur, Long> {
    int deleteByEmail(String email);
    int deleteByName(String email);
    Fournisseur findByEmail(String email);
    Fournisseur findByName(String email);
    boolean existsByEmail(String email);
}
