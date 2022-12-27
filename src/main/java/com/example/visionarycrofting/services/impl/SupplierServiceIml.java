package com.example.visionarycrofting.services.impl;

import com.example.visionarycrofting.entity.Supplier;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.exception.NotFoundException;
import com.example.visionarycrofting.repository.SupplierDao;
import com.example.visionarycrofting.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class SupplierServiceIml implements SupplierService {
    private final SupplierDao supplierDao;

    @Autowired
    public SupplierServiceIml(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Override
    public int deleteByEmail(String email) {
        if(!this.existsByEmail(email)) {
            throw new NotFoundException("Bayer with email: " + email + " does not exists");
        }
        return supplierDao.deleteByEmail(email);
    }

    @Override
    public int deleteByName(String email) {
        return supplierDao.deleteByName(email);
    }

    @Override
    public Supplier findByEmail(String email) {
        return supplierDao.findByEmail(email);
    }

    @Override
    public Supplier findByName(String email) {
        return supplierDao.findByName(email);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierDao.findAll();
    }

    @Deprecated
    @Override
    public Supplier getOne(Long aLong) {
        return supplierDao.getOne(aLong);
    }

    @Override
    public Supplier save(Supplier fournisseur) {
        if (this.existsByEmail(fournisseur.getEmail()))
            throw new BadRequestException("Email "+fournisseur.getEmail()+" is token");
//        if (validateEmail(fournisseur.getEmail()))
//            throw new BadRequestException("Email is not valid");
        return supplierDao.save(fournisseur);
    }
    @Override
    @Transactional
    public Supplier update(Supplier fournisseur) {
        Supplier byEmail = this.findByEmail(fournisseur.getEmail());
        if (byEmail == null)
            throw new BadRequestException("Email is not valid");
        if (!fournisseur.getPassword().equals(""))
            byEmail.setPassword(fournisseur.getPassword());
        if (!fournisseur.getPhone().equals(""))
            byEmail.setPhone(fournisseur.getPhone());
        if (!fournisseur.getName().equals(""))
            byEmail.setName(fournisseur.getName());
        return byEmail;
    }

    @Override
    public boolean existsByEmail(String email) {
        return supplierDao.existsByEmail(email);
    }

    @Override
    public boolean validateEmail(String emial){
        return emial.equals("");
    }
}
