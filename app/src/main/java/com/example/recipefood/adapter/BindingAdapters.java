package com.example.recipefood.adapter;

// BindingAdapters.java

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipefood.R;
import com.example.recipefood.model.ModelMain;

import java.util.List;

public class BindingAdapters {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_error)
                .into(view);
    }

    @BindingAdapter("items")
    public static void setItems(RecyclerView recyclerView, List<ModelMain> items) {
        MainAdapter adapter = (MainAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setCategories(items);
        }
    }
}
