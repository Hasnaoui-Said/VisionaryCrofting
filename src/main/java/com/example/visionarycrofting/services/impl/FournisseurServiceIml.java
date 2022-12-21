package com.example.visionarycrofting.services.impl;

import com.example.visionarycrofting.entity.Fournisseur;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.exception.NotFoundException;
import com.example.visionarycrofting.repository.FournisseurDao;
import com.example.visionarycrofting.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class FournisseurServiceIml implements FournisseurService {
    private final FournisseurDao fournisseurDao;

    @Autowired
    public FournisseurServiceIml(FournisseurDao fournisseurDao) {
        this.fournisseurDao = fournisseurDao;
    }

    @Override
    public int deleteByEmail(String email) {
        if(!this.existsByEmail(email)) {
            throw new NotFoundException("Bayer with email: " + email + " does not exists");
        }
        return fournisseurDao.deleteByEmail(email);
    }

    @Override
    public int deleteByName(String email) {
        return fournisseurDao.deleteByName(email);
    }

    @Override
    public Fournisseur findByEmail(String email) {
        return fournisseurDao.findByEmail(email);
    }

    @Override
    public Fournisseur findByName(String email) {
        return fournisseurDao.findByName(email);
    }

    @Override
    public List<Fournisseur> findAll() {
        return fournisseurDao.findAll();
    }

    @Deprecated
    @Override
    public Fournisseur getOne(Long aLong) {
        return fournisseurDao.getOne(aLong);
    }

    @Override
    public Fournisseur save(Fournisseur fournisseur) {
        if (this.existsByEmail(fournisseur.getEmail()))
            throw new BadRequestException("Email "+fournisseur.getEmail()+" is token");
//        if (validateEmail(fournisseur.getEmail()))
//            throw new BadRequestException("Email is not valid");
        return fournisseurDao.save(fournisseur);
    }
    @Override
    @Transactional
    public Fournisseur update(Fournisseur fournisseur) {
        Fournisseur byEmail = this.findByEmail(fournisseur.getEmail());
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
        return fournisseurDao.existsByEmail(email);
    }

    @Override
    public boolean validateEmail(String emial){
        return emial.equals("");
    }
}
