package com.example.npe_06_shoplants;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteFragment extends Fragment implements View.OnClickListener {

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