package org.vc.visionarycroftingmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(path = {"/","", "/index", "/index/", "/index/*", "index.*"})
    public String home(){
        return "index";
    }

}
