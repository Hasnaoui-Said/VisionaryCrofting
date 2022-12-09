package org.vc.visionarycroftingmvc.services.impl;

import org.vc.visionarycroftingmvc.models.entity.Product;
import org.vc.visionarycroftingmvc.models.entity.Stock;
import org.vc.visionarycroftingmvc.enumeration.Category;
import org.vc.visionarycroftingmvc.repository.ProductDao;
import org.vc.visionarycroftingmvc.services.ProductService;
import org.vc.visionarycroftingmvc.services.StockService;
import org.vc.visionarycroftingmvc.util.StringUtil;
import org.vc.visionarycroftingmvc.voDTO.ProductVo;
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
        Product product = productDao.findByRef(ref);
        return product;
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
        if (stockService.existsByName(product.getStock().getName()))
            return null;
//            throw new BadRequestException("Stock name is not valid");
        Stock stock = stockService.findByName(product.getStock().getName());
        product.setStock(stock);
        if (this.existsByRef(product.getRef()))
            return null;
//            throw new BadRequestException("Product ref not found");
        return productDao.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product) {
        if (!this.existsByRef(product.getRef()))
            return null;
//            throw new BadRequestException("Product ref already exist");
        Product byRef = this.findByRef(product.getRef());
        if (product.getStock() != null) {
            String nameStock = product.getStock().getName();
            if (!nameStock.equals("")) {
                Stock stock = stockService.findByName(nameStock);
                if (stock != null || stock.getName() != nameStock)
                    byRef.setStock(stock);
            }
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
