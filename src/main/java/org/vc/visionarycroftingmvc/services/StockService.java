package org.vc.visionarycroftingmvc.services;


import org.vc.visionarycroftingmvc.models.entity.Stock;

import java.util.List;


public interface StockService {

    Stock findByName(String ref);

    Stock findByEmail(String email);

    List<Stock> findAllByAddress(String address);

    int deleteByEmail(String ref);

    int deleteByName(String ref);

    List<Stock> findAll();

    Stock getOne(Long aLong);

    Stock save(Stock stock);
    Stock update(Stock stock);
    boolean existsByName(String name);
}
