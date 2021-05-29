package com.example.npe_06_shoplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.npe_06_shoplants.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private TextInputLayout tfUsername, tfEmail, tfPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MaterialButton btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        TextView tvSignIn = findViewById(R.id.tvSignIn);
        tvSignIn.setOnClickListener(this);

        tfUsername = findViewById(R.id.tfUsername);
        tfEmail = findViewById(R.id.tfEmail);
        tfPassword = findViewById(R.id.tfPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignUp) {
            String username = Objects.requireNonNull(tfUsername.getEditText()).getText().toString().trim();
            String email = Objects.requireNonNull(tfEmail.getEditText()).getText().toString().trim();
            String password = Objects.requireNonNull(tfPassword.getEditText()).getText().toString().trim();
            signUp(username, email, password);
        } else if (view.getId() == R.id.tvSignIn) {
            Intent signInIntent = new Intent(SignUpActivity.this, SignInActivity.class);
            signInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(signInIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    private boolean validateField(TextInputLayout textInputLayout, String text) {
        if (text.isEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Field can't be empty.");
            return false;
        }

        textInputLayout.setErrorEnabled(false);
        return true;
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            tfEmail.setErrorEnabled(true);
            tfEmail.setError("Field can't be empty.");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tfEmail.setErrorEnabled(true);
            tfEmail.setError("Please provide valid email.");
            return false;
        } else {
            tfEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            tfPassword.setErrorEnabled(true);
            tfPassword.setError("Field can't be empty.");
            return false;
        } else if (password.length() < 6) {
            tfPassword.setErrorEnabled(true);
            tfPassword.setError("Password must have at least 6 characters.");
            return false;
        } else {
            tfPassword.setErrorEnabled(false);
            return true;
        }
    }

    private void signUp(String username, String email, String password) {
        if (!validateField(tfUsername, username) | !validateEmail(email) | !validatePassword(password)) {
            return;
        }

        tfUsername.setErrorEnabled(false);
        tfEmail.setErrorEnabled(false);
        tfPassword.setErrorEnabled(false);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User users = new User(username, email);
                    FirebaseDatabase.getInstance("https://shoplants-c2e1e-default-rtdb.asia-southeast1.firebasedatabase.app/")
                            .getReference("User")
                            .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                            .setValue(users)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
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
                } else {
                    Toast.makeText(SignUpActivity.this, "failed registration", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}