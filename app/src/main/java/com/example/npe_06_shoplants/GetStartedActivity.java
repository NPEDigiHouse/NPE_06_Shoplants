package com.example.npe_06_shoplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class GetStartedActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        MaterialButton btnGetStarted = findViewById(R.id.btnGetStarted);
        btnGetStarted.setOnClickListener(this);

        TextView tvSignUp = findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnGetStarted) {
            Intent signInIntent = new Intent(GetStartedActivity.this, SignInActivity.class);
            startActivity(signInIntent);
        } else if (view.getId() == R.id.tvSignUp) {
            Intent signUpIntent = new Intent(GetStartedActivity.this, SignUpActivity.class);
            startActivity(signUpIntent);
        }
    }
}