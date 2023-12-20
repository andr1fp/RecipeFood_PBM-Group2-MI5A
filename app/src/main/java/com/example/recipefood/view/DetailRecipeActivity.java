package com.example.recipefood.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.recipefood.R;
import com.example.recipefood.model.ModelDetailRecipe;
import com.example.recipefood.viewmodel.DetailRecipeViewModel;

public class DetailRecipeActivity extends AppCompatActivity {

    private ImageView imgThumb;
    private TextView tvTitle, tvSubTitle, tvYoutube, tvSource, tvIngredients, tvMeasure, tvInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);

        initViews();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("idMeal")) {
            String idMeal = intent.getStringExtra("idMeal");
            Log.d("DetailRecipeActivity", "Recipe ID: " + idMeal);

            // Null check untuk ViewModel
            DetailRecipeViewModel viewModel = new ViewModelProvider(this).get(DetailRecipeViewModel.class);

            // Observasi LiveData
            viewModel.getIsLoading().observe(this, isLoading -> {
                if (isLoading) {
                    // Tampilkan indikator loading
                } else {
                    // Sembunyikan indikator loading
                }
            });

            viewModel.getError().observe(this, error -> {
                // Tangani error, tampilkan pesan error, atau log
                Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
            });

            viewModel.getDetailRecipe(idMeal).observe(this, this::setRecipeDetail);

        } else {
            // Tangani kasus di mana extra "recipeId" tidak disediakan
            // Misalnya, tampilkan pesan error atau selesaikan aktivitas
            Toast.makeText(this, "Recipe ID not provided", Toast.LENGTH_SHORT).show();
            finish(); // Opsional: selesaikan aktivitas
        }
    }

    private void initViews() {
        imgThumb = findViewById(R.id.imgThumb);
        tvTitle = findViewById(R.id.tvTitle);
        tvSubTitle = findViewById(R.id.tvSubTitle);
        tvYoutube = findViewById(R.id.tvYoutube);
        tvSource = findViewById(R.id.tvSource);
        tvIngredients = findViewById(R.id.tvIngredients);
        tvMeasure = findViewById(R.id.tvMeasure);
        tvInstructions = findViewById(R.id.tvInstructions);
    }

    private void setRecipeDetail(@Nullable ModelDetailRecipe recipe) {
        // Null check untuk objek ModelDetailRecipe
        if (recipe != null) {
            Glide.with(this).load(recipe.getStrMealThumb()).into(imgThumb);
            tvTitle.setText(recipe.getStrMeal());
            tvSubTitle.setText(String.format("%s • %s", recipe.getStrCategory(), recipe.getStrArea()));
            tvYoutube.setText(recipe.getStrYoutube());
            tvSource.setText(recipe.getStrSource());

            // Set Ingredients and Measures
            StringBuilder ingredients = new StringBuilder();
            StringBuilder measures = new StringBuilder();

            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                ingredients.append(recipe.getIngredients().get(i)).append("\n");
                measures.append(recipe.getMeasures().get(i)).append("\n");
            }

            tvIngredients.setText(ingredients.toString());
            tvMeasure.setText(measures.toString());

            tvInstructions.setText(recipe.getStrInstructions());
        } else {
            // Tangani kasus resep null
            Log.e("DetailRecipeActivity", "Recipe is null");
        }
    }
}




