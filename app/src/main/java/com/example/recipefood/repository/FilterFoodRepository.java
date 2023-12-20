package com.example.recipefood.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipefood.model.ModelFilter;
import com.example.recipefood.networking.ApiClient;
import com.example.recipefood.networking.ApiResponse;
import com.example.recipefood.networking.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterFoodRepository {

    private final ApiService apiService;

    public FilterFoodRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public LiveData<List<ModelFilter>> getFilteredList(String category) {
        MutableLiveData<List<ModelFilter>> filteredListLiveData = new MutableLiveData<>();

        // Panggil API menggunakan Retrofit
        Call<ApiResponse> call = apiService.getFilteredFoods(category, "");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    // Ambil array meals dari respons dan set ke LiveData
                    List<ModelFilter> filteredList = response.body().getMeals();
                    filteredListLiveData.setValue(filteredList);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Tangani kesalahan jaringan atau kesalahan lainnya
            }
        });

        return filteredListLiveData;
    }

    public void loadFilteredFoods(String category) {
        // Implementasikan jika diperlukan
        // Misalnya, untuk menyimpan data ke lokal database atau melakukan tugas tambahan
    }
}
