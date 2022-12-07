package com.example.visionarycrofting.services;

import com.example.visionarycrofting.entity.Stock;

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
