package org.vc.visionarycroftingmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping(path = {"/stock/product/","/stock/product","/stock/product/index","/stock/product/index/"})
    public String product(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "1") int size){
        Page<Product> productsPage = productService.findAll(PageRequest.of(page, size));
        model.addAttribute("productList", productsPage.getContent());
        model.addAttribute("pages", new int[productsPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("maxPage", productsPage.getSize());
        return "stock/product";
    }
    @GetMapping(path = {"/stock/offer/","/stock/offer"})
    public String offer(){
        return "stock/offer";
    }

}
