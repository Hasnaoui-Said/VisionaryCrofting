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

    @DeleteMapping("/ref/{ref}")
    public ResponseEntity<ResponseObject<?>> deleteByRef(String ref) {
        int i = commadItemService.deleteByRef(ref);
        String message = "Command item deleted successfully";
        Boolean result = true;
        HttpStatus status = HttpStatus.OK;
        if (i != 1){
            message = "Command item not found!!";
            result = false;
            status = HttpStatus.BAD_REQUEST;
        }
        ResponseObject<Boolean> responseObject = new ResponseObject<>(true,
                message, result);
        return new ResponseEntity<>(responseObject, status);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<CommandItem>> responseObject = new ResponseObject<>(true,
                "Find all offer", commadItemService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>>  save(@RequestBody CommandItem commandItem) {
        try {
            CommandItem create = commadItemService.save(commandItem);
            ResponseObject<CommandItem> responseObject = new ResponseObject<>(true,
                    "CommandItem Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseObject<?>>  update(@RequestBody CommandItem commandItem) {
        try {
            CommandItem create = commadItemService.update(commandItem);
            ResponseObject<CommandItem> responseObject = new ResponseObject<>(true,
                    "CommandItem Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
//
//    @GetMapping("/exists/{ref}")
//    public boolean existsByRef(@PathVariable String ref) {
//        return commadItemService.existsByRef(ref);
//    }

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
