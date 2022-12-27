package com.example.visionarycrofting.repository;

import com.example.visionarycrofting.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDao extends JpaRepository<Supplier, Long> {
    int deleteByEmail(String email);
    int deleteByName(String email);
    Supplier findByEmail(String email);
    Supplier findByName(String email);
    boolean existsByEmail(String email);
}
