package com.example.npe_06_shoplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.navHome, HomeFragment.newInstance());
        fragmentMap.put(R.id.navSearch, SearchFragment.newInstance());
        fragmentMap.put(R.id.navFavorite, FavoriteFragment.newInstance());
        fragmentMap.put(R.id.navCart, CartFragment.newInstance());

        BottomNavigationView bnvMain = findViewById(R.id.bnvMain);
        bnvMain.setOnNavigationItemSelectedListener(this);
        bnvMain.setSelectedItemId(R.id.navHome);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragmentMap.get(item.getItemId());

        assert fragment != null;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainer, fragment)
                .commit();

        return true;
    }
}