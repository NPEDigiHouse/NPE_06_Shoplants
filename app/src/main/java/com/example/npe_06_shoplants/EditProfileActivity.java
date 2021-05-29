package com.example.npe_06_shoplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.npe_06_shoplants.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EditProfileActivity extends AppCompatActivity {
    private EditText etUsernameEdit, etEmailEdit, etPassword;
    private FirebaseAuth mAuth;
    private Button btnSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar tbEditProfile = findViewById(R.id.tbEditProfile);
        tbEditProfile.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24);

        setSupportActionBar(tbEditProfile);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        etUsernameEdit = findViewById(R.id.etUsernameEdit);
        etPassword = findViewById(R.id.etPasswordEdit);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance("https://shoplants-c2e1e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String usernameVal = "";
                if(snapshot.child("username").getValue() != null){
                    usernameVal = snapshot.child("username").getValue().toString();
                }

                etUsernameEdit.setText(usernameVal);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUsername = etUsernameEdit.getText().toString();

                User newUser = new User(newUsername,mAuth.getCurrentUser().getEmail());

                FirebaseDatabase.getInstance("https://shoplants-c2e1e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference()
                        .child("User")
                        .child(mAuth.getCurrentUser().getUid()).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EditProfileActivity.this, "Username Updated", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(EditProfileActivity.this, "Failed to update username", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                if(etPassword.getText().toString().length() > 0){
                    FirebaseUser currentUser = mAuth.getCurrentUser();

                    currentUser.updatePassword(etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(EditProfileActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(EditProfileActivity.this, "Failed To Update Password", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // saat memencet icon back pada toolbar
        if (item.getItemId() == android.R.id.home) finish();
        return true;
    }
}