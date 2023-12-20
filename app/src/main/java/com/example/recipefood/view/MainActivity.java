package com.example.recipefood.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefood.R;
import com.example.recipefood.adapter.MainAdapter;
import com.example.recipefood.model.ModelMain;
import com.example.recipefood.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi ViewModel
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Inisialisasi RecyclerView dan Adapter
        recyclerView = findViewById(R.id.rvMainMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter = new MainAdapter(this, null);

        // Set item click listener pada adapter
        mainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ModelMain modelMain) {
                // Handle item click, misalnya, navigasi ke aktivitas filter resep
                // (sesuaikan dengan kebutuhan aplikasi Anda)
                Toast.makeText(MainActivity.this, "Category: " + modelMain.getStrCategory(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, FilterFoodActivity.class);
                intent.putExtra("CATEGORY", modelMain.getStrCategory());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(mainAdapter);

        // Observasi perubahan data kategori dari ViewModel
        mainViewModel.getCategories().observe(this, new Observer<List<ModelMain>>() {
            @Override
            public void onChanged(List<ModelMain> categories) {
                // Jika data berubah, set data ke adapter
                mainAdapter.setCategories(categories);
            }
        });

        // Panggil metode ViewModel untuk mendapatkan data kategori
        mainViewModel.getCategories();
    }
}
