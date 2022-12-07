package com.example.visionarycrofting.controller;

import com.example.visionarycrofting.entity.AppelOffre;
import com.example.visionarycrofting.entity.Product;
import com.example.visionarycrofting.entity.Stock;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.modes.ResponseObject;
import com.example.visionarycrofting.services.AppelOffreService;
import com.example.visionarycrofting.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.endpoint}/stock")
public class StockWs {
    @Autowired
    StockService stockService;
    @Autowired
    AppelOffreService appelOffreService;

    @GetMapping("/offer/close/ref/{refOffre}")
    public ResponseEntity<ResponseObject<?>> closeOffer(@PathVariable String refOffre) {
        try {
            AppelOffre create = appelOffreService.closeOffer(refOffre);
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(true,
                    " Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/offer/open/ref/{refOffre}")
    public ResponseEntity<ResponseObject<?>>  openOffer(String refOffre) {
        try {
            AppelOffre create = appelOffreService.openOffer(refOffre);
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(true,
                    " Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/offer/open")
    public ResponseEntity<ResponseObject<?>>  getOffreOpen() {

        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find all command item", appelOffreService.getOffreOpen());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/offer/valid")
    public ResponseEntity<ResponseObject<?>>  getOfferValid() {

        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find all command item", appelOffreService.getOffreValidee());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/offer/close")
    public ResponseEntity<ResponseObject<?>>  getOfferClose() {
        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find all command item", appelOffreService.getOffreClose());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseObject<?>>  findByName(@PathVariable String name) {
        ResponseObject<Stock> responseObject = new ResponseObject<>(true,
                "Find all command item", stockService.findByName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseObject<?>>  findByEmail(@PathVariable String email) {
        ResponseObject<Stock> responseObject = new ResponseObject<>(true,
                "Find all command item", stockService.findByEmail(email));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/email/{email}")
    public int deleteByEmail(@PathVariable String email) {
        return stockService.deleteByEmail(email);
    }

    @DeleteMapping("/name/{name}")
    public int deleteByName(@PathVariable String name) {
        return stockService.deleteByName(name);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>>  getAll() {
        ResponseObject<List<Stock>> responseObject = new ResponseObject<>(true,
                "Find all command item", stockService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>>  save(@RequestBody Stock stock) {
        try {
            Stock create = stockService.save(stock);
            ResponseObject<Stock> responseObject = new ResponseObject<>(true,
                    " Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>>  update(@RequestBody Stock stock) {
        try {
            Stock create = stockService.update(stock);
            ResponseObject<Stock> responseObject = new ResponseObject<>(true,
                    " Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<ResponseObject<?>>  findAllByAddress(@PathVariable String address) {
        ResponseObject<List<Stock>> responseObject = new ResponseObject<>(true,
                "Find all command item", stockService.findAllByAddress(address));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
