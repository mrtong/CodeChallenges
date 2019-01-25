package com.foo.pactera.recipefinder.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanjuntong on 16/04/15.
 */
public class Recipe implements Serializable {

    private final String name;

    private final List<Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}