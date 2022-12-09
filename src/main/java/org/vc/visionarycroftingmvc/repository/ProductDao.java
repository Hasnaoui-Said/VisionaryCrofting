package org.vc.visionarycroftingmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vc.visionarycroftingmvc.models.entity.Product;
import org.vc.visionarycroftingmvc.enumeration.Category;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    List<Product> findByPrixGreaterThanEqual(long prix);
    List<Product> findByNameAndPrixGreaterThanEqual(String name, long prix);
    List<Product> findByNameAndQuantityGreaterThanEqual(String name, int q);
    List<Product> findByStockEmail(String email);
    List<Product> findByStockName(String email);
    List<Product> findByCategory(Category category);
    Product findByRef(String ref);
    int deleteByRef(String ref);
    boolean existsByRef(String ref);
}
