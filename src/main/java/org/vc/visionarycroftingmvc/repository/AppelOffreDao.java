package org.vc.visionarycroftingmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vc.visionarycroftingmvc.models.entity.AppelOffre;
import org.vc.visionarycroftingmvc.enumeration.StatusAppel;

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
