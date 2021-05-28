package com.example.npe_06_shoplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class SignInEmailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_email);

        MaterialButton btnSignInEmail = findViewById(R.id.btnSignInEmail);
        btnSignInEmail.setOnClickListener(this);

        ImageView ivSignInWithPhone = findViewById(R.id.ivSignInWithPhone);
        ivSignInWithPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivSignInWithPhone) {
            finish();
        } else if (view.getId() == R.id.btnSignInEmail) {
            Intent home = new Intent(SignInEmailActivity.this, MainActivity.class);
            startActivity(home);
        }
    }
}