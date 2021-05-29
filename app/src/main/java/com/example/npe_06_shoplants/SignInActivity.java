package com.example.npe_06_shoplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private ImageView googleSignIn;
    private GoogleSignInClient mGoogleSignInClient;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        MaterialButton btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

        TextView tvSignUp = findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(this);

        ImageView ivSignInWithEmail = findViewById(R.id.ivSignInWithEmail);
        ivSignInWithEmail.setOnClickListener(this);

        login = findViewById(R.id.btnSignIn);
        googleSignIn = findViewById(R.id.ivSignInWithEmail);
        mAuth = FirebaseAuth.getInstance();



    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignIn) {
            Intent mainIntent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(mainIntent);
        } else if (view.getId() == R.id.tvSignUp) {
            Intent signUpIntent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(signUpIntent);
        }
    }

}