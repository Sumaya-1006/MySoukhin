package com.example.mysoukhin.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mysoukhin.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnView;
    FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnView = findViewById(R.id.bottomNavigationView);
        actionButton = findViewById(R.id.actionBtn);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(),AddProductActivity.class);
                 startActivity(intent);
            }
        });
        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    loadFragment(new HomeFragment(),true);


                } else if (id == R.id.fav) {
                    loadFragment(new FavouriteFragment(),false);


                } else if (id == R.id.carts){
                    loadFragment(new CartsFragment(),false);

                } else if (id == R.id.profile) {
                    loadFragment(new ProfileFragment(),false);

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

        bnView.setSelectedItemId(R.id.home);
        bnView.setItemIconTintList(iconColorStates);
    }
    public void loadFragment (Fragment fragment, boolean flag ){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_layout, fragment);
        ft.commit();

    }


}



