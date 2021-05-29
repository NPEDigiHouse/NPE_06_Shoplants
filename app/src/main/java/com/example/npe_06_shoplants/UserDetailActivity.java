package com.example.npe_06_shoplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView username, email;
    private SharedPreferences preferences;
    String loginMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Toolbar tbUserDetail = findViewById(R.id.tbUserDetail);
        tbUserDetail.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24);

        setSupportActionBar(tbUserDetail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        preferences = getSharedPreferences("loginMethod", MODE_PRIVATE);
        loginMethod = preferences.getString("method", "other");

        if (!loginMethod.equals("signInWithEmailAndPassword")) {
            findViewById(R.id.navEditProfile).setVisibility(View.GONE);
        }

        Button btnSignOut = findViewById(R.id.btnSignOut);
        CircleImageView civUserImage = findViewById(R.id.civUserImage);
        username = findViewById(R.id.tvUserName);
        email = findViewById(R.id.tvUserEmail);
        mAuth = FirebaseAuth.getInstance();


        currentUser = mAuth.getCurrentUser();

        FirebaseDatabase.getInstance("https://shoplants-c2e1e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference()
                .child("User")
                .child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String usernameVal;
                String emailVal;
                if (snapshot.child("username").getValue() != null) {
                    usernameVal = Objects.requireNonNull(snapshot.child("username").getValue()).toString();
                } else {
                    usernameVal = currentUser.getDisplayName();
                }

                if (snapshot.child("email").getValue() != null) {
                    emailVal = Objects.requireNonNull(snapshot.child("email").getValue()).toString();
                } else {
                    emailVal = currentUser.getEmail();
                }

                username.setText(usernameVal);
                email.setText(emailVal);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(View view) {
                mAuth.signOut();

                preferences.edit().putString("method", "");

                GoogleSignIn.getClient(
                        UserDetailActivity.this,
                        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                ).signOut();

                startActivity(new Intent(UserDetailActivity.this, SignInActivity.class));
                finish();
            }
        });

        if (currentUser.getPhotoUrl() != null) {
            Glide.with(this).load(currentUser.getPhotoUrl()).into(civUserImage);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // saat memencet icon back pada toolbar
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        // saat memencet icon manage profile pada toolbar
        else if (item.getItemId() == R.id.navEditProfile) {
            if (loginMethod.equals("signInWithEmailAndPassword")) {
                Intent editProfile = new Intent(UserDetailActivity.this, EditProfileActivity.class);
                startActivity(editProfile);
            }

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