package com.example.recipefood.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipefood.model.ModelMain;
import com.example.recipefood.networking.ApiClient;
import com.example.recipefood.networking.ApiResponse;
import com.example.recipefood.networking.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private final ApiService apiService;

    public MainRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public LiveData<List<ModelMain>> getCategories() {
        MutableLiveData<List<ModelMain>> categoriesData = new MutableLiveData<>();

        apiService.getCategories().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("MainRepository", "Categories fetched successfully");
                    categoriesData.setValue(response.body().getCategories());
                } else {
                    Log.e("MainRepository", "Failed to get categories. Response: " + response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("MainRepository", "Failed to get categories", t);
            }
        });

        return categoriesData;
    }
}
