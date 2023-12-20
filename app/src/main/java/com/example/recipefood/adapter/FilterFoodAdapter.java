// FilterFoodAdapter.java

package com.example.recipefood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipefood.R;
import com.example.recipefood.databinding.ListItemFilterFoodBinding;
import com.example.recipefood.model.ModelFilter;

import java.util.List;

public class FilterFoodAdapter extends RecyclerView.Adapter<FilterFoodAdapter.ViewHolder> {

    private Context context;
    private List<ModelFilter> filteredList;
    private static OnItemClickListener onItemClickListener;

    // Interface untuk menangani event klik pada item
    public interface OnItemClickListener {
        void onItemClick(ModelFilter modelFilter);
    }

    // Constructor adapter
    public FilterFoodAdapter(Context context, List<ModelFilter> filteredList) {
        this.context = context;
        this.filteredList = filteredList;
    }

    // Setter untuk listener klik pada item
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemFilterFoodBinding binding = ListItemFilterFoodBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelFilter modelFilter = filteredList.get(position);
        holder.bind(modelFilter);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    // Metode untuk menetapkan data pada adapter
    public void setFilteredList(List<ModelFilter> filteredList) {
        this.filteredList = filteredList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemFilterFoodBinding binding;

        public ViewHolder(@NonNull ListItemFilterFoodBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;

            // Tidak perlu listener di sini
        }

        // Metode bind untuk mengisi data item dan menetapkan listener klik
        public void bind(ModelFilter modelFilter) {
            binding.setModelFilter(modelFilter);

            Glide.with(binding.getRoot())
                    .load(modelFilter.getStrMealThumb())
                    .placeholder(R.drawable.img_placeholder)
                    .into(binding.imgThumb);

            // Tetapkan listener klik pada item di sini
            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(modelFilter);
                }
            });
        }
    }
}
