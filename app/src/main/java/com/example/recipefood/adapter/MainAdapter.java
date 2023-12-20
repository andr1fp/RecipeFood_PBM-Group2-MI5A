// MainAdapter.java
package com.example.recipefood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipefood.R;
import com.example.recipefood.databinding.ListItemCategoriesBinding;
import com.example.recipefood.model.ModelMain;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<ModelMain> categories;
    private OnItemClickListener onItemClickListener;

    // Interface untuk menangani event klik pada item
    public interface OnItemClickListener {
        void onItemClick(ModelMain modelMain);
    }

    // Setter untuk listener klik pada item
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // Constructor adapter
    public MainAdapter(Context context, List<ModelMain> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemCategoriesBinding binding = ListItemCategoriesBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelMain category = categories.get(position);
        holder.bind(category, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        if (categories != null) {
            return categories.size();
        } else {
            return 0; // atau nilai lain sesuai kebutuhan
        }
    }

    // Metode untuk menetapkan data pada adapter
    public void setCategories(List<ModelMain> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemCategoriesBinding binding;

        public ViewHolder(@NonNull ListItemCategoriesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Metode bind untuk mengisi data item dan menetapkan listener klik
        public void bind(ModelMain category, OnItemClickListener listener) {
            binding.setCategory(category);

            Glide.with(binding.getRoot())
                    .load(category.getStrCategoryThumb())
                    .placeholder(R.drawable.img_placeholder)
                    .into(binding.imgKategori);

            // Menetapkan listener klik pada item
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(category);
                }
            });
        }
    }
}
