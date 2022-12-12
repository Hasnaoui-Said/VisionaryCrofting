package org.vc.visionarycroftingmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vc.visionarycroftingmvc.models.entity.Client;
import org.vc.visionarycroftingmvc.services.ClientService;
import org.vc.visionarycroftingmvc.voDTO.ClientDto;

import javax.servlet.http.HttpSession;

@Controller
public class ClientController {

    @Autowired
    ClientService clientService;
    @GetMapping(path = {"/sign-in",})
    public String formSignIn(Model model){
        model.addAttribute("client", new ClientDto());
        return "signIn";
    }
    @PostMapping(path = {"/sign-in",})
    public String createClient(Model model,@ModelAttribute("user") ClientDto client, HttpSession session){
        System.out.println("client");
        System.out.println(client.toString());
        ClientDto save = clientService.save(client);
        if (save != null) {
            session.setAttribute("user", save);
            return "redirect:index";
        }else{
            model.addAttribute("message_error", "error !!!");
            model.addAttribute("user", client);
            return "signIn";
        }
    }

    @PostMapping(path = {"/login",})
    public String loginUser(Model model, ClientDto client, HttpSession session){
        if (!clientService.existsByEmail(client.getEmail())){
            // login not found
            model.addAttribute("message_error","login not found!!");
            model.addAttribute("client", client);
            return "login";
        }
        Client client1 = clientService.findByEmail(client.getEmail());
        if (!client1.getPassword().equals(client.getPassword())){
            model.addAttribute("message_error","password incorrect!!");
            model.addAttribute("client", client);
            return "login";
        }
        session.setAttribute("user", client);
        return "redirect:index";
    }
    @GetMapping(path = {"/login",})
    public String logInPage(Model model, ClientDto client){
        model.addAttribute("message_error","");
        model.addAttribute("client", client);
        return "login";
    }
    @RequestMapping(value = "log-out", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:index";
    }
    @GetMapping(path = {"/patients",})
    public String patients(){
        return "patients";
    }
    @GetMapping(path = {"/shop"})
    public String shop(Model model){
        return "shop";
    }

}
