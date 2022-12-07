package com.example.visionarycrofting.services;

import com.example.visionarycrofting.entity.AppelOffre;
import com.example.visionarycrofting.enumeration.StatusAppel;

import java.util.List;

public interface AppelOffreService {

    List<AppelOffre> findAll();

    AppelOffre findByRef(String ref);

    int deleteByRef(String ref);

    List<AppelOffre> findAllByFournisseurName(String name);

    List<AppelOffre> findAllByFournisseurEmail(String email);

    List<AppelOffre> findAllByStockEmail(String email);

    List<AppelOffre> findAllByStockName(String name);

    List<AppelOffre> findAllByStatus(StatusAppel statusAppel);

    List<AppelOffre> findAllByQuantityGreaterThanEqual(int quant);

    boolean existsByRef(String ref);

    AppelOffre save(AppelOffre appelOffre);

    AppelOffre update(AppelOffre appelOffre);


    AppelOffre valideeOffre(String email, String ref);

    AppelOffre closeOffer(String refOffre);
    AppelOffre openOffer(String refOffre);

    AppelOffre getOne(Long id);

    List<AppelOffre> getOffreOpen();

    List<AppelOffre> getOffreValidee();

    List<AppelOffre> getOffreClose();

    List<AppelOffre> getOffersByName(String name);
    List<AppelOffre> getOffersByEmail(String email);
}
