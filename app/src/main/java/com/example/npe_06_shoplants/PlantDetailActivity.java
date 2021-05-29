package com.example.npe_06_shoplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.npe_06_shoplants.models.Plant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlantDetailActivity extends AppCompatActivity {

    private String name, imgUrl, description;
    private int price, id;
    private TextView tvTitle ,tvTotalOrder;
    private ImageView ivCover;
    private Button btnDecreaseOrder, btnIncreaseOrder;
    private MaterialButton btnAddtoCart;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        Toolbar tbPlantDetail = findViewById(R.id.tbPlantDetail);
        tbPlantDetail.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24);

        setSupportActionBar(tbPlantDetail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getIntExtra("Id",0);
        name = intent.getStringExtra("Name");
        imgUrl = intent.getStringExtra("Image");
        price = intent.getIntExtra("Price",0);
        description = intent.getStringExtra("Description");

        tvTitle = findViewById(R.id.tvToolbarUserDetailTitle);
        ivCover = findViewById(R.id.ivDetailPlantPhoto);

        tvTitle.setText(name);
        Glide.with(this).load(imgUrl).into(ivCover);

        btnDecreaseOrder = findViewById(R.id.btnDecreaseOrder);
        btnIncreaseOrder = findViewById(R.id.btnIncreaseOrder);
        tvTotalOrder = findViewById(R.id.tvTotalOrder);
        btnAddtoCart = findViewById(R.id.btnAddToCart);

        total = Integer.parseInt(tvTotalOrder.getText().toString());

        findViewById(R.id.favoriteIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plant plant = new Plant();
                plant.setId(id);
                plant.setName(name);
                plant.setImageUrl(imgUrl);
                plant.setPrice(price);
                plant.setDescription(description);

                FirebaseDatabase.getInstance("https://shoplants-c2e1e-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference("Favorite")
                        .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                        .child(String.valueOf(plant.getId()))
                        .setValue(plant)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PlantDetailActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(PlantDetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        btnIncreaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = total + 1;
                tvTotalOrder.setText(String.valueOf(total));
                btnAddtoCart.setText("Rp. "+ String.valueOf(total*price));
            }
        });
        btnDecreaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(total > 0){
                    total = total - 1;
                    tvTotalOrder.setText(String.valueOf(total));
                    btnAddtoCart.setText("Rp. "+ String.valueOf(total*price));
                }
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // saat memencet icon back pada toolbar
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        // saat memencet icon favorite pada toolbar
        // else if (item.getItemId() == R.id.favoriteIcon) {
        // do something here
        // }

        return true;
    }

    // method mengubah warna pada icon sebelah kanan atas (manage profile)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.plant_detail_menu, menu);

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