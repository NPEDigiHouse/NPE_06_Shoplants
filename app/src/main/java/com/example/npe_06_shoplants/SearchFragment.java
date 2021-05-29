package com.example.npe_06_shoplants;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npe_06_shoplants.adapters.SearchPlantsAdapter;
import com.example.npe_06_shoplants.data.PlantsData;
import com.example.npe_06_shoplants.models.Plant;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchFragment extends Fragment implements View.OnClickListener {
    private List<Plant> plants;
    private RecyclerView recyclerView;
    private EditText searchValue;

    public static SearchFragment newInstance() {
        SearchFragment searchFragment = new SearchFragment();
        Bundle args = new Bundle();
        // args.putString(MainActivity.TOOLBAR_TITLE, "search");
        searchFragment.setArguments(args);
        return searchFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        CircleImageView civImageProfile = view.findViewById(R.id.civImageProfile);
        civImageProfile.setOnClickListener(this);

        searchValue = view.findViewById(R.id.etSearchBar);

        plants = PlantsData.getPlantsData();

        recyclerView = view.findViewById(R.id.rvSearchedPlants);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SearchPlantsAdapter(plants));

        searchValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        return view;
    }

    private void filter(String text) {
        List<Plant> filtered = new ArrayList<>();

        for (Plant e : plants) {
            if (e.getName().toLowerCase().contains(text.toLowerCase())) {
                filtered.add(e);
            }
        }

        recyclerView.setAdapter(new SearchPlantsAdapter(filtered));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.civImageProfile) {
            Intent userDetail = new Intent(getActivity(), UserDetailActivity.class);
            startActivity(userDetail);
        }
    }
}