package com.taco.taco.controllers;

import com.taco.taco.TacoApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // private final TacoApplication tacoApplication;

    // HomeController(TacoApplication tacoApplication) {
    //     this.tacoApplication = tacoApplication;
    // }
    
    @GetMapping("/")
    public String Home(){
        return "home";
    }
}
