package com.example.npe_06_shoplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.npe_06_shoplants.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private EditText etUsername, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MaterialButton btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        TextView tvSignIn = findViewById(R.id.tvSignIn);
        tvSignIn.setOnClickListener(this);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignUp) {
            singUp();
        } else if (view.getId() == R.id.tvSignIn) {
            finish();
        }
    }

    private void singUp() {
        String email = etEmail.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(username.isEmpty()){
            etUsername.setError("Please insert username");
            etUsername.requestFocus();
            return;
        }

        if(email.isEmpty()){
            etEmail.setError("Please insert email");
            etUsername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            etEmail.setError("Please insert password");
            etUsername.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User users = new User(username,email);
                    FirebaseDatabase.getInstance("https://shoplants-c2e1e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "success register", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(SignUpActivity.this, "failed registration",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}