package com.example.visionarycrofting.repository;

import com.example.visionarycrofting.entity.AppelOffre;
import com.example.visionarycrofting.enumeration.StatusAppel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppelOffreDao extends JpaRepository<AppelOffre, Long> {
    AppelOffre findByRef(String ref);
    int deleteByRef(String ref);
    List<AppelOffre> findAllByFournisseurName(String name);
    List<AppelOffre> findAllByFournisseurEmail(String email);
    List<AppelOffre> findAllByStockEmail(String email);
    List<AppelOffre> findAllByStockName(String name);
    List<AppelOffre> findAllByStatus(StatusAppel statusAppel);
    List<AppelOffre> findAllByQuantityGreaterThanEqual(int quant);
    boolean existsByRef(String ref);
}
