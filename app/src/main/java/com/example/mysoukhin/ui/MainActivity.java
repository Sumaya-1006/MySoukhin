package com.example.mysoukhin.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mysoukhin.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnView;
    FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnView = findViewById(R.id.bottomNavigationView);
        actionButton = findViewById(R.id.actionBtn);
        // badge = findViewById(R.id.badge_id);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddProductActivity.class);
                startActivity(intent);
            }
        });

        HomeFragment homeFragment = new HomeFragment();
        FavouriteFragment favouriteFragment = new FavouriteFragment();
        CartsFragment cartsFragment = new CartsFragment();
        ProfileFragment profileFragment = new ProfileFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();

                } else if (id == R.id.fav) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, favouriteFragment).commit();

                } else if (id == R.id.carts) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, cartsFragment).commit();


                } else if (id == R.id.profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, profileFragment).commit();

                }


                return true;
            }
        });
        ColorStateList iconColorStates = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{
                        Color.parseColor("#9A9998"),
                        Color.parseColor("#553DF2")
                });

        bnView.setItemIconTintList(iconColorStates);
       // bnView.getOrCreateBadge(R.id.carts).setNumber(Count);


        // badge.setText("4");
    }


    }





