package com.example.npe_06_shoplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class SignInPhoneActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_phone);

        MaterialButton btnSignInPhone = findViewById(R.id.btnSignInPhone);
        btnSignInPhone.setOnClickListener(this);

        ImageView ivSignInWithEmail = findViewById(R.id.ivSignInWithEmail);
        ivSignInWithEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivSignInWithEmail) {
            Intent signInEmail = new Intent(SignInPhoneActivity.this, SignInEmailActivity.class);
            startActivity(signInEmail);
        } else if (view.getId() == R.id.btnSignInPhone) {
            Intent home = new Intent(SignInPhoneActivity.this, MainActivity.class);
            startActivity(home);
        }
    }
}