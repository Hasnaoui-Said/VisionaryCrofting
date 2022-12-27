package com.example.visionarycrofting.controller;

import com.example.visionarycrofting.entity.AppelOffre;
import com.example.visionarycrofting.entity.Command;
import com.example.visionarycrofting.entity.Product;
import com.example.visionarycrofting.enumeration.Category;
import com.example.visionarycrofting.exception.BadRequestException;
import com.example.visionarycrofting.modes.ResponseObject;
import com.example.visionarycrofting.services.ProductService;
import com.example.visionarycrofting.voDTO.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${api.endpoint}/product")
public class ProductWs {
    @Autowired
    ProductService productService;

    @GetMapping("/prix/{prix}")
    public ResponseEntity<ResponseObject<?>> findByPrixGreaterThanEqual(long prix) {
        ResponseObject<List<Product>> responseObject = new ResponseObject<>(true,
                "findByPrixGreaterThanEqual", productService.findByPrixGreaterThanEqual(prix));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/ref/{ref}")
    public ResponseEntity<ResponseObject<?>> findByRef(String ref) {
        ResponseObject<Product> responseObject = new ResponseObject<>(true,
                "findByRef", productService.findByRef(ref));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(String ref) {
        return productService.deleteByRef(ref);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject<?>> findAll() {
        ResponseObject<List<Product>> responseObject = new ResponseObject<>(true,
                "Find all products", productService.findAll());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<ResponseObject<?>> findByCriteria(@RequestBody ProductVo product) {
        ResponseObject<List<Product>> responseObject = new ResponseObject<>(true,
                "find By Criteria", productService.findByCriteria(product));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject<?>> save(@RequestBody @Valid Product product) {
        try {
            Product create = productService.save(product);
            ResponseObject<Product> responseObject = new ResponseObject<>(true,
                    "Product Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/")
    public ResponseEntity<ResponseObject<?>> update(@RequestBody @Valid Product product) {
        try {
            Product create = productService.update(product);
            ResponseObject<Product> responseObject = new ResponseObject<>(true,
                    "Product Created Successfully", create);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (BadRequestException e) {
            ResponseObject<AppelOffre> responseObject = new ResponseObject<>(false,
                    e.getMessage(), null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name/{name}/prix/{prix}")
    public ResponseEntity<ResponseObject<?>> findByNameAndPrixGreaterThanEqual(@PathVariable String name, @PathVariable long prix) {
        ResponseObject<List<Product>> responseObject = new ResponseObject<>(true,
                "find By Name And Price Greater Than Equal", productService.findByNameAndPrixGreaterThanEqual(name, prix));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/name/{name}/quantity/{q}")
    public ResponseEntity<ResponseObject<?>> findByNameAndQuantityGreaterThanEqual(@PathVariable String name, @PathVariable int q) {
        ResponseObject<List<Product>> responseObject = new ResponseObject<>(true,
                "findByNameAndQuantityGreaterThanEqual", productService.findByNameAndQuantityGreaterThanEqual(name, q));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/stock/email/{email}")
    public ResponseEntity<ResponseObject<?>> findByStockEmail(@PathVariable String email) {
        ResponseObject<List<Product>> responseObject = new ResponseObject<>(true,
                "findByStockEmail", productService.findByStockEmail(email));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/stock/name/{name}")
    public ResponseEntity<ResponseObject<?>> findByStockName(@PathVariable String name) {
        ResponseObject<List<Product>> responseObject = new ResponseObject<>(true,
                "findByStockName", productService.findByStockName(name));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ResponseObject<?>> findByCategory(@PathVariable Category category) {
        ResponseObject<List<Product>> responseObject = new ResponseObject<>(true,
                "findByCategory", productService.findByCategory(category));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
