package org.vc.visionarycroftingmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vc.visionarycroftingmvc.models.entity.Command;

import java.util.List;

@Repository
public interface CommandDao extends JpaRepository<Command, Long> {
    Command findByRef(String ref);
    int deleteByRef(String ref);
    List<Command> findAllByClientEmailAndPrixTotalGreaterThanEqual(String email, double total);
    List<Command> findAllByClientEmail(String email);
    boolean existsByRef(String ref);
}
