package com.example.recipefood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipefood.model.ModelFilter;
import com.example.recipefood.repository.FilterFoodRepository;

import java.util.List;

public class FilterFoodViewModel extends ViewModel {

    private final FilterFoodRepository repository;

    public FilterFoodViewModel() {
        repository = new FilterFoodRepository();
    }

    public LiveData<List<ModelFilter>> getFilteredList(String category) {
        return repository.getFilteredList(category);
    }

    public void loadFilteredFoods(String category) {
        repository.loadFilteredFoods(category);
    }
}
