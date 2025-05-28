package com.taco.taco.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.taco.taco.data.Order;
import com.taco.taco.data.models.OrderRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepository){
        this.orderRepo = orderRepository;
    }

    
    @GetMapping("/ current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus){
        if (errors.hasErrors()) {
            return "orderForm";
        }
        
        log.info("order submitted" + order);
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }


}
