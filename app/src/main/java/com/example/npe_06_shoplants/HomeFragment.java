package com.example.npe_06_shoplants;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.npe_06_shoplants.models.Plant;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Plant> list;

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
       return inflater.inflate(R.layout.fragment_home, container, false);
    }
}