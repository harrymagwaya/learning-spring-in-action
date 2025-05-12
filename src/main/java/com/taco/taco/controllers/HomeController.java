package com.taco.taco.controllers;

import com.taco.taco.TacoApplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

/*
 * Another way to add a controllfer that does nothing but direct to a view
 *
 */

//  @Configuration
//  class webConfig implements WebMvcConfigurer {
//     @Override
//     public void addViewControllers(ViewControllerRegistry registry) {
        
//         registry.addViewController("/").setViewName("home");
//     }

//  }
