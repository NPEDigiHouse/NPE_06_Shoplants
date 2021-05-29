package com.example.npe_06_shoplants;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npe_06_shoplants.adapters.FavoritePlantsAdapter;
import com.example.npe_06_shoplants.models.Plant;
import com.example.npe_06_shoplants.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteFragment extends Fragment implements View.OnClickListener {

    private List<Plant> plants;
    private RecyclerView recyclerView;

    public static FavoriteFragment newInstance() {
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        Bundle args = new Bundle();
        // args.putString(MainActivity.TOOLBAR_TITLE, "favorite");
        favoriteFragment.setArguments(args);

        return favoriteFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        CircleImageView civImageProfile = view.findViewById(R.id.civImageProfile);
        civImageProfile.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.rvRecommendedPlants);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));

        FirebaseDatabase.getInstance("https://shoplants-c2e1e-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Favorite")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                plants = new ArrayList<>();

                for(DataSnapshot item : snapshot.getChildren()){
                    Plant plant = item.getValue(Plant.class);
                    plants.add(plant);
                }
                recyclerView.setAdapter(new FavoritePlantsAdapter(plants));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.civImageProfile) {
            Intent userDetail = new Intent(getActivity(), UserDetailActivity.class);
            startActivity(userDetail);
        }
    }
}