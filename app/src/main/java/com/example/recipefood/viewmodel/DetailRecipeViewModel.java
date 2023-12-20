package com.example.recipefood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipefood.model.ModelDetailRecipe;
import com.example.recipefood.repository.DetailRecipeRepository;

public class DetailRecipeViewModel extends ViewModel {

    private LiveData<ModelDetailRecipe> detailRecipe;
    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private DetailRecipeRepository repository;

    public DetailRecipeViewModel() {
        repository = new DetailRecipeRepository();
    }

    public LiveData<ModelDetailRecipe> getDetailRecipe(String recipeId) {
        if (detailRecipe == null) {
            detailRecipe = repository.fetchDetailRecipe(recipeId, isLoading, error);
        }
        return detailRecipe;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}



