package com.example.npe_06_shoplants.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.npe_06_shoplants.PlantDetailActivity;
import com.example.npe_06_shoplants.R;
import com.example.npe_06_shoplants.models.Plant;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SearchPlantsAdapter extends RecyclerView.Adapter<SearchPlantsAdapter.ViewHolder> {
    private List<Plant> plants;

    public SearchPlantsAdapter(List<Plant> plants){
        this.plants = plants;
    }

    @NonNull
    @NotNull
    @Override
    public SearchPlantsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchPlantsAdapter.ViewHolder holder, int position) {
        final Plant plant = plants.get(position);
        Glide.with(holder.itemView.getContext()).load(plant.getImageUrl()).into(holder.ivSearchPlant);
        holder.tvSearchPlantName.setText(plant.getName());
        holder.tvSearchPlantPrice.setText(String.valueOf(plant.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), PlantDetailActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSearchPlant;
        TextView tvSearchPlantName, tvSearchPlantPrice;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            ivSearchPlant = itemView.findViewById(R.id.ivSearchPlantPhoto);
            tvSearchPlantName = itemView.findViewById(R.id.tvSearchPlantName);
            tvSearchPlantPrice = itemView.findViewById(R.id.tvSearchPlantPrice);
        }
    }
}
