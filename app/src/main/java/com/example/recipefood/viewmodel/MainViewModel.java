package com.example.recipefood.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipefood.model.ModelMain;
import com.example.recipefood.repository.MainRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final MainRepository repository;
    private LiveData<List<ModelMain>> categories;

    public MainViewModel() {
        repository = new MainRepository();
        categories = repository.getCategories();
    }

    public LiveData<List<ModelMain>> getCategories() {
        Log.d("MainViewModel", "Fetching categories...");
        return categories;
    }
}
