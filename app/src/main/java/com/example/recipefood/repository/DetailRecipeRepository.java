package com.example.recipefood.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.recipefood.model.ModelDetailRecipe;
import com.example.recipefood.networking.ApiClient;
import com.example.recipefood.networking.ApiResponse;
import com.example.recipefood.networking.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRecipeRepository {

    private MutableLiveData<ModelDetailRecipe> detailRecipeData;

    public MutableLiveData<ModelDetailRecipe> fetchDetailRecipe(String recipeId,
                                                                MutableLiveData<Boolean> isLoading,
                                                                MutableLiveData<String> error) {
        if (detailRecipeData == null) {
            detailRecipeData = new MutableLiveData<>();
        }

        isLoading.setValue(true); // Set loading state to true before making the API call

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse> call = apiService.getDetailRecipes(recipeId);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                isLoading.setValue(false); // Set loading state to false when the response is received

                if (response.isSuccessful()) {
                    List<ModelDetailRecipe> detailRecipes = response.body().getDetailRecipes();
                    if (detailRecipes != null && !detailRecipes.isEmpty()) {
                        detailRecipeData.setValue(detailRecipes.get(0));
                    }
                } else {
                    handleFailure("Unsuccessful response: " + response.code(), error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                isLoading.setValue(false); // Set loading state to false on failure
                Log.e("DetailRecipeRepository", "Failed to fetch data: " + t.getMessage());
                handleFailure("Failed to fetch data. Please try again.", error);
            }
        });

        return detailRecipeData;
    }

    private void handleFailure(String message, MutableLiveData<String> error) {
        error.setValue(message);
        // Anda dapat mengimplementasikan logika tambahan di sini untuk menangani kegagalan
        // Misalnya, log error atau perbarui UI sesuai
        Log.e("DetailRecipeRepository", message);
    }
}