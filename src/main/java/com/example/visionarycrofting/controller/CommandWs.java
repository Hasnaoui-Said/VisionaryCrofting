package com.example.visionarycrofting.controller;

import com.example.visionarycrofting.entity.AppelOffre;
import com.example.visionarycrofting.entity.Command;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.modes.ResponseObject;
import com.example.visionarycrofting.services.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("${api.endpoint}/command")
public class CommandWs {
    @Autowired
    CommandService commandService;

    @GetMapping("/ref/{ref}")
    public ResponseEntity<ResponseObject<?>> findByRef(String ref) {
        ResponseObject<Command> responseObject = new ResponseObject<>(true,
                "Find all command item", commandService.findByRef(ref));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/ref/{ref}")
    public ResponseEntity<ResponseObject<?>> deleteByRef(String ref) {
        int i = commandService.deleteByRef(ref);
        boolean success = true;
        if (i != 0)
            success = false;
        ResponseObject<Integer> responseObject = new ResponseObject<>(success,
                "Delete By ref", i);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody @Valid Command command) {
        try {
            Command create = commandService.save(command);
            ResponseObject<Command> responseObject = new ResponseObject<>(true,
                    "Command Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<Command>> responseObject = new ResponseObject<>(true,
                "Find All", commandService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/client/email/{email}/total/{total}")
    public ResponseEntity<ResponseObject<?>> findAllByClientEmailAndPrixTotalGreaterThanEqual(@PathVariable String email, @PathVariable double total) {
        ResponseObject<List<Command>> responseObject = new ResponseObject<>(true,
                "Find All", commandService.findAllByClientEmailAndPrixTotalGreaterThanEqual(email, total));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/client/email/{email}")
    public ResponseEntity<ResponseObject<?>> findAllByClientEmail(@PathVariable String email) {
        ResponseObject<List<Command>> responseObject = new ResponseObject<>(true,
                "Find By client email", commandService.getAllByClientEmail(email));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

}
