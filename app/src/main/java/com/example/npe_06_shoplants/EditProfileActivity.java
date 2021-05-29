package com.example.npe_06_shoplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar tbEditProfile = findViewById(R.id.tbEditProfile);
        tbEditProfile.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24);

        setSupportActionBar(tbEditProfile);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // saat memencet icon back pada toolbar
        if (item.getItemId() == android.R.id.home) finish();
        return true;
    }
}