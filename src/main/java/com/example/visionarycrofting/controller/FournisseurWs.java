package com.example.visionarycrofting.controller;

import com.example.visionarycrofting.entity.AppelOffre;
import com.example.visionarycrofting.entity.Command;
import com.example.visionarycrofting.entity.Fournisseur;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.modes.ResponseObject;
import com.example.visionarycrofting.services.AppelOffreService;
import com.example.visionarycrofting.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint}/fournisseur")
public class FournisseurWs {
    @Autowired
    FournisseurService fournisseurService;
    @Autowired
    AppelOffreService appelOffreService;

    @GetMapping("/offer/fournisseur/{email}/valid/{ref}")
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

    @DeleteMapping("/name/{name}")
    public ResponseEntity<ResponseObject<?>> deleteByEmail(@PathVariable String email) {
        int i = fournisseurService.deleteByEmail(email);
        boolean success = true;
        if (i != 0)
            success = false;
        ResponseObject<Integer> responseObject = new ResponseObject<>(success,
                "Find By ref", i);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<ResponseObject<?>> deleteByName(@PathVariable String email) {
        int i = fournisseurService.deleteByName(email);
        boolean success = true;
        if (i != 0)
            success = false;
        ResponseObject<Integer> responseObject = new ResponseObject<>(success,
                "Find By ref", i);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseObject<?>> findByEmail(@PathVariable String email) {
        ResponseObject<Fournisseur> responseObject = new ResponseObject<>(true,
                "Find all command item", fournisseurService.findByEmail(email));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseObject<?>> findByName(@PathVariable String name) {
        ResponseObject<Fournisseur> responseObject = new ResponseObject<>(true,
                "Find all command item", fournisseurService.findByName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<Fournisseur>> responseObject = new ResponseObject<>(true,
                "Find all command item", fournisseurService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody Fournisseur fournisseur) {
        try {
            Fournisseur create = fournisseurService.save(fournisseur);
            ResponseObject<Fournisseur> responseObject = new ResponseObject<>(true,
                    "Command Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody Fournisseur fournisseur) {
        try {
            Fournisseur create = fournisseurService.update(fournisseur);
            ResponseObject<Fournisseur> responseObject = new ResponseObject<>(true,
                    "Command Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
