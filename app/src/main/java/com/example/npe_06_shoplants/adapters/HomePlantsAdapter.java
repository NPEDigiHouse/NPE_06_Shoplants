package com.example.npe_06_shoplants.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.npe_06_shoplants.PlantDetailActivity;
import com.example.npe_06_shoplants.R;
import com.example.npe_06_shoplants.models.Plant;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomePlantsAdapter extends RecyclerView.Adapter<HomePlantsAdapter.ViewHolder> {
    private final List<Plant> plants;

    public HomePlantsAdapter(List<Plant> plants) {
        this.plants = plants;
    }

    @NonNull
    @NotNull
    @Override
    public HomePlantsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_plant, parent, false);
        return new HomePlantsAdapter.ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull @NotNull HomePlantsAdapter.ViewHolder holder, int position) {
        final Plant plant = plants.get(position);

        Glide.with(holder.itemView.getContext())
                .load(plant.getImageUrl())
                .apply(new RequestOptions().override(320, 400))
                .into(holder.ivHomePlant);

        holder.tvHomePlantName.setText(plant.getName());
        holder.tvHomePlantPrice.setText(String.format("Rp.%d", plant.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), PlantDetailActivity.class);
                intent.putExtra("Name", plant.getName());
                intent.putExtra("Image", plant.getImageUrl());
                intent.putExtra("Price", plant.getPrice());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHomePlant;
        TextView tvHomePlantName, tvHomePlantPrice;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            ivHomePlant = itemView.findViewById(R.id.ivHomePlantPhoto);
            tvHomePlantName = itemView.findViewById(R.id.tvHomePlantName);
            tvHomePlantPrice = itemView.findViewById(R.id.tvHomePlantPrice);
        }
    }
}
