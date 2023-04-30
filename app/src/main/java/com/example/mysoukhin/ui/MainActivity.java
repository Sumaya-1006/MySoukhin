package com.example.mysoukhin.ui;

import static com.example.mysoukhin.ui.ProductDetailsActivity.badge;
import static com.example.mysoukhin.ui.ProductDetailsActivity.count;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.CartItemModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnView;
    FloatingActionButton actionButton;
    DatabaseReference root1;
    DatabaseReference reference;
    static int totalCount = 1;

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
        reference = FirebaseDatabase.getInstance().getReference("cart");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               /* for(DataSnapshot ds : snapshot.getChildren()){
                    int sum =0;
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object Count = map.get("count");
                    Log.d("count", String.valueOf(Count));

                    if (Count != null) {
                                totalCount = Integer.parseInt(String.valueOf(Count));
                                Log.d("totalCount", String.valueOf(totalCount));
                                sum += totalCount;
                                Log.d("sum", String.valueOf(sum));
                                bnView.getOrCreateBadge(R.id.carts).setNumber(sum);
                            }
                        }
                    }*/
                if (snapshot.exists()) {
                    int sum =0;
                    sum = (int) snapshot.getChildrenCount();
                    bnView.getOrCreateBadge(R.id.carts).setNumber(sum);

                }
            }

                @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    }





