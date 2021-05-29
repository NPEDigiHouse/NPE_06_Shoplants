package com.example.npe_06_shoplants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MaterialButton btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        TextView tvSignIn = findViewById(R.id.tvSignIn);
        tvSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignUp) {
            Toast.makeText(this, "Your account has been create! Please login.", Toast.LENGTH_SHORT).show();
            finish();
        } else if (view.getId() == R.id.tvSignIn) {
            finish();
        }
    }
}