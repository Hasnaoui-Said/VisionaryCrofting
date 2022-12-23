package com.example.visionarycrofting.services;

import com.example.visionarycrofting.entity.Supplier;

import java.util.List;


public interface SupplierService {
    int deleteByEmail(String email);

    int deleteByName(String email);

    Supplier findByEmail(String email);

    Supplier findByName(String email);

    List<Supplier> findAll();

    Supplier getOne(Long aLong);

    Supplier save(Supplier fournisseur);
    Supplier update(Supplier fournisseur);

    boolean existsByEmail(String email);

    boolean validateEmail(String emial);
}
