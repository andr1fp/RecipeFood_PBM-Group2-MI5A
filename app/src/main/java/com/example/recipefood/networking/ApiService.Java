package com.example.recipefood.networking;

import com.example.recipefood.model.ModelDetailRecipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("categories.php")
    Call<ApiResponse> getCategories();

    @GET("filter.php")
    Call<ApiResponse> getFilteredFoods(@Query("c") String category, @Query("i") String ingredient);

    @GET("lookup.php")
    Call<ApiResponse> getDetailRecipes(@Query("i") String mealId);
}