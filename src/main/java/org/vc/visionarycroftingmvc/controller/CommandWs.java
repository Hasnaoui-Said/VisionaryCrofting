package org.vc.visionarycroftingmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vc.visionarycroftingmvc.models.domains.ResponseObject;
import org.vc.visionarycroftingmvc.models.entity.Command;
import org.vc.visionarycroftingmvc.models.entity.Product;
import org.vc.visionarycroftingmvc.services.CommandService;

@RestController
@RequestMapping("/api/v1/command")
public class CommandWs {

    @Autowired
    CommandService commandService;

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody Command command) {
        try {
            Command create = commandService.saveCommand(command);
            ResponseObject<Command> responseObject = new ResponseObject<>(true,
                    "Command Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (Exception e) {
            ResponseObject<Product> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
