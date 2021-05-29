package com.example.npe_06_shoplants;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.npe_06_shoplants.adapters.HomePlantsAdapter;
import com.example.npe_06_shoplants.adapters.SearchPlantsAdapter;
import com.example.npe_06_shoplants.data.PlantsData;
import com.example.npe_06_shoplants.models.Plant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private List<Plant> plants;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        // args.putString(MainActivity.TOOLBAR_TITLE, "home");
        homeFragment.setArguments(args);

        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        CircleImageView civImageProfile = view.findViewById(R.id.civImageProfile);
        civImageProfile.setOnClickListener(this);

        plants = PlantsData.getPlantsData();
        recyclerView = view.findViewById(R.id.rvHomePlants);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new HomePlantsAdapter(plants));

        assert currentUser != null;
        if (currentUser.getPhotoUrl() != null) {
            Glide.with(view).load(currentUser.getPhotoUrl()).into(civImageProfile);
        }

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