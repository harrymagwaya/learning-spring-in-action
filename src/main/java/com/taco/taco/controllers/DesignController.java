package com.taco.taco.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.taco.taco.data.Ingredient;
import com.taco.taco.data.Order;
import com.taco.taco.data.Taco;
import com.taco.taco.data.Ingredient.Type;
import com.taco.taco.data.models.IngredientRepository;
import com.taco.taco.data.models.TacoRepository;

import jakarta.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignController {

    private final IngredientRepository ingredientRepo;

    private TacoRepository designRepo;

    @Autowired
    public DesignController(IngredientRepository ingredientRepository, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepository;
        this.designRepo = designRepo;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        // List<Ingredient> ingredients = Arrays.asList(
        //         new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        //         new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        //         new Ingredient("GRBF", "Ground beef", Type.PROTEIN),
        //         new Ingredient("CARN", "Carnitas", Type.WRAP),
        //         new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        //         new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        //         new Ingredient("CHED", "Cheddar", Type.CHEESE),
        //         new Ingredient("SLSA", "Salsa", Type.SAUCE),
        //         new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        List <Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

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

            @ModelAttribute(name = "order")
            public Order order() {
                return new Order();
            }

            @ModelAttribute(name = "taco")
            public Taco taco() {
                return new Taco();
            }       

    @PostMapping
    public String processDesign(
        @Valid Taco design, 
        Errors errors,
        @ModelAttribute Order order ){


            if (errors.hasErrors()) {
                return "design";
            }

            Taco savedTaco = designRepo.save(design);

            order.addDesign(savedTaco);

            log.info("Processing design: " + design);
            return "redirect:/orders/current";
    }


  

    
    
}
