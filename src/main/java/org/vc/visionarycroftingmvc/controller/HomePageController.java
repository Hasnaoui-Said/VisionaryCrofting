package org.vc.visionarycroftingmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vc.visionarycroftingmvc.models.entity.Product;
import org.vc.visionarycroftingmvc.services.ProductService;
import org.vc.visionarycroftingmvc.voDTO.ProductVo;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    ProductService productService;
    @GetMapping(path = {"/","", "/index", "/index/", "/index/*", "index.*"})
    public String home(Model model,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "20") int size){
        Page<Product> listProduct = productService.findAll(PageRequest.of(page, size));
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("productC", new ProductVo());
        return "index";
    }
    @PostMapping(path = {"/findByCriteria"})
    public String findByCriteria(Model model,@ModelAttribute("productC") ProductVo productC,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "20") int size){
        List<Product> listProduct = productService.findByCriteria(productC);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("productC", productC);
        return "index";
    }

}
