package org.vc.visionarycroftingmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vc.visionarycroftingmvc.models.entity.Fournisseur;

@Repository
public interface FournisseurDao extends JpaRepository<Fournisseur, Long> {
    int deleteByEmail(String email);
    int deleteByName(String email);
    Fournisseur findByEmail(String email);
    Fournisseur findByName(String email);
    boolean existsByEmail(String email);
}
