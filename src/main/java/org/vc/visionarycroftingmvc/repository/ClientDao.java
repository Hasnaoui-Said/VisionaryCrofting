package org.vc.visionarycroftingmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vc.visionarycroftingmvc.models.entity.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Long> {
    int deleteByEmail(String email);
    Client findByEmail(String email);
    boolean existsByEmail(String s);
}
