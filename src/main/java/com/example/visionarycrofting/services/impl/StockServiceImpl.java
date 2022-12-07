package com.example.visionarycrofting.services.impl;

import com.example.visionarycrofting.entity.AppelOffre;
import com.example.visionarycrofting.entity.Stock;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.repository.StockDao;
import com.example.visionarycrofting.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockDao stockDao;

    @Override
    public Stock findByName(String ref) {
        return stockDao.findByName(ref);
    }

    @Override
    public Stock findByEmail(String email) {
        return stockDao.findByEmail(email);
    }

    @Override
    public List<Stock> findAllByAddress(String address) {
        return stockDao.findAllByAddress(address);
    }

    @Override
    @Transactional
    public int deleteByEmail(String ref) {
        return stockDao.deleteByEmail(ref);
    }

    @Override
    @Transactional
    public int deleteByName(String ref) {
        return stockDao.deleteByName(ref);
    }

    @Override
    public List<Stock> findAll() {
        return stockDao.findAll();
    }

    @Deprecated
    @Override
    public Stock getOne(Long aLong) {
        return stockDao.getOne(aLong);
    }

    @Override
    public Stock save(Stock stock) {
        if (this.findByName(stock.getName()) != null)
            throw new BadRequestException("Stock name already exist!!");
        return stockDao.save(stock);
    }

    @Override
    @Transactional
    public Stock update(Stock stock) {
        Stock stock1 = this.findByName(stock.getName());
        if (stock1 == null)
            throw new BadRequestException("Stock name not found!!");
        if (!stock.getAddress().equals(""))
            stock1.setAddress(stock.getAddress());
        if (!stock.getEmail().equals(""))
            stock1.setEmail(stock.getEmail());
        if (!stock.getPassword().equals(""))
            stock1.setPassword(stock.getPassword());
        if (!stock.getPhone().equals(""))
            stock1.setPhone(stock.getPhone());
        return stock1;
    }

    @Override
    public boolean existsByName(String name) {
        return stockDao.existsByName(name);
    }
}
