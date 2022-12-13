package org.vc.visionarycroftingmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vc.visionarycroftingmvc.models.domains.ResponseObject;
import org.vc.visionarycroftingmvc.models.entity.Product;
import org.vc.visionarycroftingmvc.services.ProductService;
import org.vc.visionarycroftingmvc.voDTO.ProductVo;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/ref/{ref}")
    public ResponseEntity<ResponseObject<?>> findByRef(@PathVariable String ref) {
        ResponseObject<Product> responseObject = new ResponseObject<>(true,
                "Find all command item", productService.findByRef(ref));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<ResponseObject<?>> findByCriteria(@RequestBody ProductVo product) {
        ResponseObject<List<Product>> responseObject = new ResponseObject<>(true,
                "Find all command item", productService.findByCriteria(product));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> getAll(@RequestBody ProductVo product) {
        ResponseObject<Page<Product>> responseObject = new ResponseObject<>(true,
                "Find all command item", productService.findAll(PageRequest.of(0, 20)));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody Product product) {
        try {
            Product create = productService.save(product);
            ResponseObject<Product> responseObject = new ResponseObject<>(true,
                    "Command Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (Exception e) {
            ResponseObject<Product> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
