package com.example.visionarycrofting.controller;

import com.example.visionarycrofting.entity.AppelOffre;
import com.example.visionarycrofting.enumeration.StatusAppel;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.modes.ResponseObject;
import com.example.visionarycrofting.services.AppelOffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint}/appel-offre")
public class AppelOffreWs {
    @Autowired
    AppelOffreService appelOffreService;

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> getAll() {
        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find all offer", appelOffreService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/ref/{ref}")
    public ResponseEntity<ResponseObject<?>> findByRef(@PathVariable String ref) {
        AppelOffre offer = appelOffreService.findByRef(ref);
        HttpStatus status;
        if (offer == null) status = HttpStatus.NOT_FOUND;
        else status = HttpStatus.OK;
        ResponseObject<AppelOffre> responseObject = new ResponseObject<>(true,
                "Find offer by ref {" + ref + "}", offer);
        return new ResponseEntity<>(responseObject, status);
    }

    @DeleteMapping("/ref/{ref}")
    public ResponseEntity<ResponseObject<?>> deleteByRef(@PathVariable String ref) {
        int expected = appelOffreService.deleteByRef(ref);
        HttpStatus status = HttpStatus.OK;
        if (expected != 1) status = HttpStatus.NOT_FOUND;
        ResponseObject<String> responseObject = new ResponseObject<>(true,
                "Find offer by ref {" + ref + "}", ref);
        return new ResponseEntity<>(responseObject, status);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody AppelOffre appelOffre) {
        try {
            AppelOffre create = appelOffreService.save(appelOffre);
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(true,
                    "Offer Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody AppelOffre appelOffre) {
        try {
            AppelOffre create = appelOffreService.update(appelOffre);
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(true,
                    "Offer updated Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<Throwable> responseObject = new ResponseObject<>(false,
                    e.getMessage(), e.getCause());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fournisseur/name/{name}")
    public ResponseEntity<ResponseObject<List<AppelOffre>>> findAllByFournisseurName(String name) {
        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find all offer", appelOffreService.findAllByFournisseurName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/fournisseur/email/{email}")
    public ResponseEntity<ResponseObject<List<AppelOffre>>> findAllByFournisseurEmail(@PathVariable String email) {
        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find all offer", appelOffreService.findAllByFournisseurEmail(email));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/stock/email/{email}")
    public ResponseEntity<ResponseObject<List<AppelOffre>>> findAllByStockEmail(@PathVariable String email) {
        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find all offer", appelOffreService.findAllByStockEmail(email));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/stock/name/{name}")
    public ResponseEntity<ResponseObject<List<AppelOffre>>> findAllByStockName(@PathVariable String name) {
        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find all offer", appelOffreService.findAllByStockName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ResponseObject<List<AppelOffre>>> findAllByStatus(@PathVariable StatusAppel status) {
        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find all offer", appelOffreService.findAllByStatus(status));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/quantity/quant/{quant}")
    public ResponseEntity<ResponseObject<List<AppelOffre>>> findAllByQuantityGreaterThanEqual(@PathVariable int quant) {
        ResponseObject<List<AppelOffre>> responseObject = new ResponseObject<>(true,
                "Find all offer", appelOffreService.findAllByQuantityGreaterThanEqual(quant));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/exists/ref/{ref}")
    public ResponseEntity<ResponseObject<?>> existsByRef(@PathVariable String ref) {
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                "Check if exist", appelOffreService.existsByRef(ref));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}






