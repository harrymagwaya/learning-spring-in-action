package com.taco.taco.controllers;

import java.util.Arrays;
import java.util.List;
import com.taco.taco.TacoApplication;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taco.taco.data.Ingredient;
import com.taco.taco.data.Taco;
import com.taco.taco.data.Ingredient.Type;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/design")
public class DesignController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.WRAP),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                 filterByType(ingredients, type)); 
            }
                        model.addAttribute("design", new Taco());
                         return "design";
                     }
                 
                     private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
                        return ingredients.stream()
                                          .filter(x->x.getType().equals(type))
                                          .collect(Collectors.toList());
                     }

    // @PostMapping
    // public String processDesign(Design design){
    //     log.info("Processing design: " + design);
    //     return "redirect:/orders/current";
    // }
    
}
