package com.example.visionarycrofting.controller;

import com.example.visionarycrofting.entity.Client;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.modes.ResponseObject;
import com.example.visionarycrofting.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint}/client")
public class CleintWs {
    @Autowired
    ClientService clientService;

    @DeleteMapping("/email/{email}")
    public ResponseEntity<ResponseObject<?>> deleteByEmail(@PathVariable String email) {
        String message;
        if (clientService.deleteByEmail(email) == 1)
            message = "Client deleted successfully";
        else message = "Client whit this parameter not fount";

        ResponseObject<String> ResponseObject = new ResponseObject<>(true,
                message, email);
        return new ResponseEntity<>(ResponseObject, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {

        ResponseObject<Client> ResponseObject = new ResponseObject<>(true,
                "find client by email", clientService.findByEmail(email));
        return new ResponseEntity<>(ResponseObject, HttpStatus.OK);
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<ResponseObject<?>> existsByEmail(@PathVariable String email) {

        String message;
        boolean succues = false;
        boolean existsByEmail = clientService.existsByEmail(email);
        if (existsByEmail){
            message = "Email exist";
            succues = true;
        }
        else message = "Email not found";

        ResponseObject<Boolean> ResponseObject = new ResponseObject<>(succues,
                message, existsByEmail);
        return new ResponseEntity<>(ResponseObject, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {

        List<Client> clients = clientService.findAll();
        ResponseObject<List<Client>> ResponseObject = new ResponseObject<>(true,
                "listing clients", clients);

        return new ResponseEntity<>(ResponseObject, HttpStatus.OK);
    }

    @Deprecated
    @GetMapping("/id/{a}")
    public Client getOne(@PathVariable Long a) {
        return clientService.getOne(a);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody Client client) {
        try {
            Client createClient = clientService.save(client);
            ResponseObject<Client> responseObject = new ResponseObject<>(true,
                    "Client Created Successfully", createClient);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<Throwable> responseObject = new ResponseObject<>(false,
                    e.getMessage(), e.getCause());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody Client client) {
        try {
            Client createClient = clientService.update(client);
            ResponseObject<Client> responseObject = new ResponseObject<>(true,
                    "Client updated Successfully", createClient);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (BadRequestException e){
            ResponseObject<Throwable> responseObject = new ResponseObject<>(false,
                    e.getMessage(), e.getCause());
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

}
