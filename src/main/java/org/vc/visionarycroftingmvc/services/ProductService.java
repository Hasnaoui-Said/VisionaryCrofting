package org.vc.visionarycroftingmvc.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.vc.visionarycroftingmvc.models.entity.Product;
import org.vc.visionarycroftingmvc.enumeration.Category;
import org.vc.visionarycroftingmvc.voDTO.ProductVo;

import java.util.List;


public interface ProductService{
    List<Product> findByCriteria(ProductVo productVo);

    List<Product> findByPrixGreaterThanEqual(long prix);

    List<Product> findByNameAndPrixGreaterThanEqual(String name, long prix);

    List<Product> findByNameAndQuantityGreaterThanEqual(String name, int q);

    List<Product> findByStockEmail(String email);

    List<Product> findByStockName(String email);

    List<Product> findByCategory(Category category);

    Product findByRef(String ref);

    int deleteByRef(String ref);

    Page<Product> findAll(PageRequest request);

    Product getOne(Long aLong);

    Product save(Product product);
    Product update(Product product);

    boolean existsByRef(String ref);
}
