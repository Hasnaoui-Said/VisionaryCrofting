package org.vc.visionarycroftingmvc.services.impl;

import org.vc.visionarycroftingmvc.models.entity.AppelOffre;
import org.vc.visionarycroftingmvc.models.entity.Fournisseur;
import org.vc.visionarycroftingmvc.models.entity.Product;
import org.vc.visionarycroftingmvc.models.entity.Stock;
import org.vc.visionarycroftingmvc.enumeration.StatusAppel;
import org.vc.visionarycroftingmvc.repository.AppelOffreDao;
import org.vc.visionarycroftingmvc.services.AppelOffreService;
import org.vc.visionarycroftingmvc.services.FournisseurService;
import org.vc.visionarycroftingmvc.services.ProductService;
import org.vc.visionarycroftingmvc.services.StockService;
import org.vc.visionarycroftingmvc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AppelOffreServiceImpl implements AppelOffreService {
    @Autowired
    AppelOffreDao appelOffreDao;
    @Autowired
    StockService stockService;
    @Autowired
    ProductService productService;
    @Autowired
    FournisseurService fournisseurService;

    @Override
    public List<AppelOffre> findAll() {
        return appelOffreDao.findAll();
    }

    @Override
    public AppelOffre findByRef(String ref) {
        return appelOffreDao.findByRef(ref);
    }

    @Override
    public int deleteByRef(String ref) {
        return appelOffreDao.deleteByRef(ref);
    }

    @Override
    public List<AppelOffre> findAllByFournisseurName(String name) {
        return appelOffreDao.findAllByFournisseurName(name);
    }

    @Override
    public List<AppelOffre> findAllByFournisseurEmail(String email) {
        return appelOffreDao.findAllByFournisseurEmail(email);
    }

    @Override
    public List<AppelOffre> findAllByStockEmail(String email) {
        return appelOffreDao.findAllByStockEmail(email);
    }

    @Override
    public List<AppelOffre> findAllByStockName(String name) {
        return appelOffreDao.findAllByStockName(name);
    }

    @Override
    public List<AppelOffre> findAllByStatus(StatusAppel statusAppel) {
        return appelOffreDao.findAllByStatus(statusAppel);
    }

    @Override
    public List<AppelOffre> findAllByQuantityGreaterThanEqual(int quant) {
        return appelOffreDao.findAllByQuantityGreaterThanEqual(quant);
    }

    @Override
    public boolean existsByRef(String ref) {
        return appelOffreDao.existsByRef(ref);
    }

    @Override
    public AppelOffre save(AppelOffre appelOffre) {
        if (this.findByRef(appelOffre.getRef()) != null)
            return null;
//            throw new BadRequestException("Stock whit this parameter name is token!!");

        Stock stock = stockService.findByName(appelOffre.getStock().getName());
        if (stock == null) {
            return null;
//            throw new BadRequestException("Stock not exist!!");
        }
        Product product = productService.findByRef(appelOffre.getRefProduct());
        if (product == null)
            return null;
//            throw new BadRequestException("Ref product not exist!!");
        if (!product.getStock().getName().equals(appelOffre.getStock().getName()))
            return null;
//            throw new BadRequestException("Error, Product whit parameter: {refProduct= " + appelOffre.getRefProduct() + "} doesn't exist on stock whit parameter: {stock= " + stock.getName() + "}");

        if (appelOffre.getQuantity() <= 0)
            return null;
//            throw new BadRequestException("Error, Offer with this parameter quantity = "+ appelOffre.getQuantity());;
        appelOffre.setStock(stock);
        appelOffre.setStatus(StatusAppel.ouverte);
        return appelOffreDao.save(appelOffre);
    }

    @Override
    @Transactional
    public AppelOffre update(AppelOffre appelOffre) {
        String ref = appelOffre.getRef();
        if (!existsByRef(ref))
            return null;
//            throw new BadRequestException("Offer not found ith this parameter ref = " + ref);
        AppelOffre offre = this.findByRef(ref);
        String nameStock = appelOffre.getStock().getName();
        if (stockService.existsByName(nameStock) && StringUtil.isEmpty(nameStock))
            return null;
//            throw new BadRequestException("Stock not found");
        Stock stock = stockService.findByName(nameStock);
        String refProduct = appelOffre.getRefProduct();
        if (!refProduct.equals(""))
            if (productService.existsByRef(refProduct)) {
                if (!productService.findByRef(refProduct).getStock().getName().equals(nameStock))
                    return null;
//                    throw new BadRequestException("Error, Product whit parameter: {refProduct= " + appelOffre.getRefProduct() + "} doesn't exist on stock whit parameter: {stock= " + stock.getName() + "}");

                offre.setRefProduct(refProduct);
            } else {
                return null;
//                throw new BadRequestException("refProduct not found");
            }
        int quantity = appelOffre.getQuantity();
        if (quantity >= 0 && quantity != offre.getQuantity())
            offre.setQuantity(quantity);
        offre.setStock(stock);
        return offre;
    }

    @Override
    @Transactional
    public AppelOffre valideeOffre(String email, String ref) {
        if (!fournisseurService.existsByEmail(email))
            return null;
//            throw new BadRequestException("Offer not found whit this ref");
        Fournisseur fournisseur = fournisseurService.findByEmail(email);
        AppelOffre offre = this.findByRef(ref);
        if (offre == null)
            return null;
//            throw new BadRequestException("Offer not found whit this ref");
        offre.setFournissour(fournisseur);
        offre.setStatus(StatusAppel.validee);
        return offre;
    }

    @Override
    @Transactional
    public AppelOffre closeOffer(String refOffre) {
        if (!existsByRef(refOffre))
            return null;
//        throw new BadRequestException("Offer not found whit this ref");
        AppelOffre offer = this.findByRef(refOffre);
        if (offer.getStatus() == StatusAppel.ouverte)
            offer.setStatus(StatusAppel.close);
        else if (offer.getStatus() == StatusAppel.validee) {
            Product product = productService.findByRef(offer.getRefProduct());
            if (product == null) {
                return null;
//                throw new BadRequestException("product not fount");
            }
            product.setQuantity(product.getQuantity() + offer.getQuantity());
            productService.update(product);
            offer.setStatus(StatusAppel.close);
        } else {
            return null;
//            throw new BadRequestException("Offer already close!!");
        }
        return offer;
    }

    @Override
    @Transactional
    public AppelOffre openOffer(String refOffre) {
        if (!this.existsByRef(refOffre))
            return null;
//            throw new BadRequestException("Offer not found whit this ref");
        AppelOffre offer = this.findByRef(refOffre);
        offer.setStatus(StatusAppel.ouverte);
        return offer;
    }

    @Deprecated
    @Override
    public AppelOffre getOne(Long id) {
        return appelOffreDao.getOne(id);
    }

    @Override
    public List<AppelOffre> getOffreOpen() {
        return appelOffreDao.findAllByStatus(StatusAppel.ouverte);
    }

    @Override
    public List<AppelOffre> getOffreValidee() {
        return appelOffreDao.findAllByStatus(StatusAppel.validee);
    }

    @Override
    public List<AppelOffre> getOffreClose() {
        return appelOffreDao.findAllByStatus(StatusAppel.close);
    }

    @Override
    public List<AppelOffre> getOffersByName(String name) {
        return appelOffreDao.findAllByFournisseurName(name);
    }

    @Override
    public List<AppelOffre> getOffersByEmail(String email) {
        return appelOffreDao.findAllByFournisseurEmail(email);
    }

}