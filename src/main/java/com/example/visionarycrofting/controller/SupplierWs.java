package com.example.visionarycrofting.controller;

import com.example.visionarycrofting.entity.AppelOffre;
import com.example.visionarycrofting.entity.Supplier;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.modes.ResponseObject;
import com.example.visionarycrofting.services.AppelOffreService;
import com.example.visionarycrofting.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${api.endpoint}/supplier")
public class SupplierWs {
    @Autowired
    SupplierService supplierService;
    @Autowired
    AppelOffreService appelOffreService;

    @GetMapping("/offer/{email}/valid/{ref}")
    public ResponseEntity<ResponseObject<?>> validOffer(@PathVariable String email, @PathVariable String ref) {
        try {
            AppelOffre create = appelOffreService.valideeOffre(email, ref);
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(true,
                    "Command Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/offer/open")
    public ResponseEntity<ResponseObject<?>> getOfferOpen() {
        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find By ref", appelOffreService.getOffreOpen());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/offer/name/{name}")
    public ResponseEntity<ResponseObject<?>> getOffersByName(@PathVariable String name) {

        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find By ref", appelOffreService.getOffersByName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/offer/email/{email}")
    public ResponseEntity<ResponseObject<?>> getOffersByEmail(@PathVariable String email) {
        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find By ref", appelOffreService.getOffersByEmail(email));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<ResponseObject<?>> deleteByEmail(@PathVariable String email) {
        int i = supplierService.deleteByEmail(email);
        boolean success = true;
        if (i != 0)
            success = false;
        ResponseObject<Integer> responseObject = new ResponseObject<>(success,
                "Find By ref", i);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<ResponseObject<?>> deleteByName(@PathVariable String name) {
        int i = supplierService.deleteByName(name);
        boolean success = true;
        if (i != 0)
            success = false;
        ResponseObject<Integer> responseObject = new ResponseObject<>(success,
                "Find By ref", i);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseObject<?>> findByEmail(@PathVariable String email) {
        ResponseObject<Supplier> responseObject = new ResponseObject<>(true,
                "Find all command item", supplierService.findByEmail(email));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseObject<?>> findByName(@PathVariable String name) {
        ResponseObject<Supplier> responseObject = new ResponseObject<>(true,
                "Find all command item", supplierService.findByName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<Supplier>> responseObject = new ResponseObject<>(true,
                "Find all command item", supplierService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")@ResponseBody
    public ResponseEntity<ResponseObject<?>> save(@RequestBody @Valid Supplier supplier, BindingResult bindingResult) {
        try {
            Supplier create = supplierService.save(supplier);
            ResponseObject<Supplier> responseObject = new ResponseObject<>(true,
                    "Command Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<Throwable> responseObject = new ResponseObject<>(false,
                    e.getMessage(), e.getCause());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody Supplier supplier) {
        try {
            Supplier create = supplierService.update(supplier);
            ResponseObject<Supplier> responseObject = new ResponseObject<>(true,
                    "Command Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
