package com.example.visionarycrofting.services;

import com.example.visionarycrofting.entity.Product;
import com.example.visionarycrofting.enumeration.Category;
import com.example.visionarycrofting.voDTO.ProductVo;

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

    List<Product> findAll();

    Product getOne(Long aLong);

    Product save(Product product);
    Product update(Product product);

    boolean existsByRef(String ref);
}
