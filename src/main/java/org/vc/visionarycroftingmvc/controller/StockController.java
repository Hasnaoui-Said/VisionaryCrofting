package org.vc.visionarycroftingmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.vc.visionarycroftingmvc.models.entity.Product;
import org.vc.visionarycroftingmvc.services.ProductService;

import java.util.List;

@Controller
public class StockController {
    @Autowired
    ProductService productService;

    @GetMapping(path = {"/stock/index/", "/stock/index", "/stock","/stock/"})
    public String index(){
        return "/stock/index";
    }
    @GetMapping(path = {"/stock/product/","/stock/product","/stock/product/index.*"})
    public String product(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("productList", products);
        return "stock/product";
    }
    @GetMapping(path = {"/stock/offer/","/stock/offer"})
    public String offer(){
        return "stock/offer";
    }

}
