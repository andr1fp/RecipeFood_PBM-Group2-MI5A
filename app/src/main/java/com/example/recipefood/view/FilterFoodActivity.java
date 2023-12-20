package com.example.recipefood.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefood.R;
import com.example.recipefood.adapter.FilterFoodAdapter;
import com.example.recipefood.model.ModelFilter;
import com.example.recipefood.viewmodel.FilterFoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class FilterFoodActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FilterFoodAdapter filterFoodAdapter;
    private FilterFoodViewModel filterFoodViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_food);

        // Inisialisasi ViewModel
        filterFoodViewModel = new ViewModelProvider(this).get(FilterFoodViewModel.class);

        // Mendapatkan data kategori yang dipilih dari intent
        String selectedCategory = getIntent().getStringExtra("CATEGORY");

        // Inisialisasi dan atur Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_filter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(selectedCategory);

        // Set judul Toolbar dengan kategori yang dipilih
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(selectedCategory);

        // Inisialisasi RecyclerView dan Adapter
        recyclerView = findViewById(R.id.rvFilter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        filterFoodAdapter = new FilterFoodAdapter(this, new ArrayList<>());

        // Set item click listener pada adapter
        filterFoodAdapter.setOnItemClickListener(new FilterFoodAdapter.OnItemClickListener() {
            public void onItemClick(ModelFilter modelFilter) {
                // Implementasikan logika navigasi ke aktivitas detail di sini
                Intent intent = new Intent(FilterFoodActivity.this, DetailRecipeActivity.class);
                intent.putExtra("idMeal", modelFilter.getIdMeal());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(filterFoodAdapter);

        // Observasi perubahan data makanan dari ViewModel
        filterFoodViewModel.getFilteredList(selectedCategory).observe(this, new Observer<List<ModelFilter>>() {
            @Override
            public void onChanged(List<ModelFilter> filteredFoods) {
                // Jika data berubah, set data ke adapter
                filterFoodAdapter.setFilteredList(filteredFoods);
            }
        });

        // Panggil metode ViewModel untuk mendapatkan data makanan berdasarkan kategori
        filterFoodViewModel.loadFilteredFoods(selectedCategory);
    }
}
