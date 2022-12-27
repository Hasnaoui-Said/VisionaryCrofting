package com.example.visionarycrofting.controller;

import com.example.visionarycrofting.entity.AppelOffre;
import com.example.visionarycrofting.entity.CommandItem;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.modes.ResponseObject;
import com.example.visionarycrofting.services.CommadItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint}/command-item")
public class CommandItemWs {
    @Autowired
    CommadItemService commadItemService;

    @GetMapping("/ref/{ref}")
    public ResponseEntity<ResponseObject<?>> findByRef(String ref) {
        ResponseObject<CommandItem> responseObject = new ResponseObject<>(true,
                "Find all command item", commadItemService.findByRef(ref));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<CommandItem>> responseObject = new ResponseObject<>(true,
                "Find all offer", commadItemService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/prix/{prix}")
    public ResponseEntity<ResponseObject<?>>  findAllByPrixGreaterThanEqual(@PathVariable long prix) {
        ResponseObject<List<CommandItem>> responseObject = new ResponseObject<>(true,
                "Find all command item", commadItemService.findAllByPrixGreaterThanEqual(prix));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/command/ref/{ref}")
    public ResponseEntity<ResponseObject<?>>  findAllByCommandRef(@PathVariable String ref) {
        ResponseObject<List<CommandItem>> responseObject = new ResponseObject<>(true,
                "Find all command item", commadItemService.findAllByCommandRef(ref));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/product/ref/{ref}")
    public ResponseEntity<ResponseObject<?>>  findAllByProductRef(@PathVariable String ref) {
        ResponseObject<List<CommandItem>> responseObject = new ResponseObject<>(true,
                "Find all command item", commadItemService.findAllByProductRef(ref));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/quant/{quant}")
    public ResponseEntity<ResponseObject<?>>  findAllByQuantityGreaterThanEqual(@PathVariable int quant) {
        ResponseObject<List<CommandItem>> responseObject = new ResponseObject<>(true,
                "Find all command item", commadItemService.findAllByQuantityGreaterThanEqual(quant));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
