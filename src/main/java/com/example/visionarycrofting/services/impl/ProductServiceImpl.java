package com.example.visionarycrofting.services.impl;

import com.example.visionarycrofting.entity.Product;
import com.example.visionarycrofting.entity.Stock;
import com.example.visionarycrofting.enumeration.Category;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.repository.ProductDao;
import com.example.visionarycrofting.services.ProductService;
import com.example.visionarycrofting.services.StockService;
import com.example.visionarycrofting.util.StringUtil;
import com.example.visionarycrofting.voDTO.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    StockService stockService;

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Product> findByCriteria(ProductVo productVo) {
        String query = "SELECT p FROM product p WHERE 1=1";
        if (!StringUtil.isEmpty(productVo.getRef()))
            query += " AND p.ref LIKE('%" + productVo.getRef() + "%')";
        if (!StringUtil.isEmpty(productVo.getName()))
            query += " AND p.name LIKE('%" + productVo.getName() + "%')";
        if (!StringUtil.isEmpty(productVo.getPrixMax()))
            query += " AND p.prix <= '" + productVo.getPrixMax() + "'";
        if (!StringUtil.isEmpty(productVo.getPrixMin()))
            query += " AND p.prix >= '" + productVo.getPrixMin() + "'";
        if (!StringUtil.isEmpty(productVo.getCategory()))
            query += " AND p.category LIKE('%" + productVo.getCategory() + "%')";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Product> findByPrixGreaterThanEqual(long prix) {
        return productDao.findByPrixGreaterThanEqual(prix);
    }

    @Override
    public List<Product> findByNameAndPrixGreaterThanEqual(String name, long prix) {
        return productDao.findByNameAndPrixGreaterThanEqual(name, prix);
    }

    @Override
    public List<Product> findByNameAndQuantityGreaterThanEqual(String name, int q) {
        return productDao.findByNameAndQuantityGreaterThanEqual(name, q);
    }

    @Override
    public List<Product> findByStockEmail(String email) {
        return productDao.findByStockEmail(email);
    }

    @Override
    public List<Product> findByStockName(String email) {
        return productDao.findByStockName(email);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productDao.findByCategory(category);
    }

    @Override
    public Product findByRef(String ref) {
        return productDao.findByRef(ref);
    }

    @Override
    public int deleteByRef(String ref) {
        return productDao.deleteByRef(ref);
    }

    @Override
    public boolean existsByRef(String ref) {
        return productDao.existsByRef(ref);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Deprecated
    @Override
    public Product getOne(Long aLong) {
        return productDao.getOne(aLong);
    }

    @Override
    public Product save(Product product) {
        if (this.existsByRef(product.getRef()))
            throw new BadRequestException("Product ref not found");
        if (stockService.existsByName(product.getStock().getName()))
            throw new BadRequestException("Stock with this parameter name is not valid");
        Stock stock = stockService.findByName(product.getStock().getName());
        product.setStock(stock);
        return productDao.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product) {
        if (!this.existsByRef(product.getRef()))
            throw new BadRequestException("Product with this ref not found");
        Product byRef = this.findByRef(product.getRef());
        if (!product.getStock().getName().equals(byRef.getStock().getName())) {
            Stock stock = stockService.findByName(product.getStock().getName());
            if (stock == null)
                throw new BadRequestException("Stock with this name not found");
            byRef.setStock(stock);
        }
        if (product.getDescription() != null)
            byRef.setDescription(product.getDescription());
        if (product.getName() != null)
            byRef.setName(product.getName());
        if (product.getPrix() > 0 && product.getPrix() != byRef.getPrix())
            byRef.setPrix(product.getPrix());
        if (product.getQuantity() != byRef.getQuantity() && product.getQuantity() >= 0)
            byRef.setQuantity(product.getQuantity());
        if (product.getCategory() != null)
            byRef.setCategory(product.getCategory());
        return byRef;
    }
}
