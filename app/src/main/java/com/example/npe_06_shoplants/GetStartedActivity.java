package com.example.npe_06_shoplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class GetStartedActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        MaterialButton getStarted = findViewById(R.id.btnGetStarted);
        getStarted.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnGetStarted) {
            Intent signInPhone = new Intent(GetStartedActivity.this, SignInPhoneActivity.class);
            startActivity(signInPhone);
        }
    }
}