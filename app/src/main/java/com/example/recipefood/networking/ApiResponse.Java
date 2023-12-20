package com.example.recipefood.networking;

import com.example.recipefood.model.ModelDetailRecipe;
import com.example.recipefood.model.ModelFilter;
import com.example.recipefood.model.ModelMain;

import java.util.List;

public class ApiResponse {
    private List<ModelMain> categories;

    public List<ModelMain> getCategories() {
        return categories;
    }

    private List<ModelFilter> meals;

    public List<ModelFilter> getMeals() {
        return meals;
    }

    private List<ModelDetailRecipe> detailRecipes;

    public List<ModelDetailRecipe> getDetailRecipes() {
        return detailRecipes;
    }
}