package org.vc.visionarycroftingmvc.repository;

import org.vc.visionarycroftingmvc.models.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDao extends JpaRepository<Stock, Long> {
    Stock findByName(String ref);
    Stock findByEmail(String email);
    List<Stock> findAllByAddress(String address);
    int deleteByEmail(String ref);
    int deleteByName(String ref);

    boolean existsByName(String name);
}
