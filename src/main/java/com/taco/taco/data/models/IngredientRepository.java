package com.taco.taco.data.models;


import com.taco.taco.data.Ingredient;

public interface  IngredientRepository {
    
    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
    
}