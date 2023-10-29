package dev.arhimedes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class HomeController {

    @GetMapping
    public String getIndex(){
        return "index";
    }

    @GetMapping("/movie")
    public String getMoviePage(){
        return "movie";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "/admin-pages/admin";
    }
}
