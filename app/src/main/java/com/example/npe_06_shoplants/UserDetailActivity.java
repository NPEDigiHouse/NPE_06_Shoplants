package com.example.npe_06_shoplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Toolbar tbUserDetail = findViewById(R.id.tbUserDetail);
        tbUserDetail.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24);

        setSupportActionBar(tbUserDetail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // saat memencet icon back pada toolbar
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        // saat memencet icon manage profile pada toolbar
        else if (item.getItemId() == R.id.navEditProfile) {
            Intent editProfile = new Intent(UserDetailActivity.this, EditProfileActivity.class);
            startActivity(editProfile);
        }

        return true;
    }

    // method mengubah warna pada icon sebelah kanan atas (manage profile)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_detail_menu, menu);

        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();

            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources()
                        .getColor(R.color.accentColor1), PorterDuff.Mode.SRC_ATOP);
            }
        }

        return true;
    }
}