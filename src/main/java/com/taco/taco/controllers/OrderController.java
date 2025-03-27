package com.taco.taco.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taco.taco.data.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final HomeController homeController;

    private final DesignController designController;

    OrderController(DesignController designController, HomeController homeController) {
        this.designController = designController;
        this.homeController = homeController;
    }
    
    @GetMapping
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(Order order){
        log.info("order submitted" + order);
        return "redirect:/";
    }
}
