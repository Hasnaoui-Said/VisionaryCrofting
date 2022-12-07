package com.example.visionarycrofting.services;

import com.example.visionarycrofting.entity.Fournisseur;

import java.util.List;


public interface FournisseurService {
    int deleteByEmail(String email);

    int deleteByName(String email);

    Fournisseur findByEmail(String email);

    Fournisseur findByName(String email);

    List<Fournisseur> findAll();

    Fournisseur getOne(Long aLong);

    Fournisseur save(Fournisseur fournisseur);
    Fournisseur update(Fournisseur fournisseur);

    boolean existsByEmail(String email);

    boolean validateEmail(String emial);
}
