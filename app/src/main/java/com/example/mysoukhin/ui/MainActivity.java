package com.example.mysoukhin.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mysoukhin.R;
import com.example.mysoukhin.models.LatestModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        HomeFragment homeFragment = new HomeFragment();
        FavouriteFragment favouriteFragment = new FavouriteFragment();
        CartsFragment cartsFragment = new CartsFragment();
        ProfileFragment profileFragment = new ProfileFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              int id = item.getItemId();

                if (id == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();

                } else if (id == R.id.fav) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,favouriteFragment).commit();

                } else if (id == R.id.carts){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,cartsFragment).commit();

                } else if (id == R.id.profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,profileFragment).commit();

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


       /* FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("products").child("Latest products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String productTitle = snapshot.getValue().toString();
                System.out.println(productTitle);

                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    if(snapshot.exists()){
                        String category = "" + dataSnapshot.child("category").getValue();
                        String oldPrice = "" + dataSnapshot.child("oldPrice").getValue();
                        String Img = "" + dataSnapshot.child("productImg").getValue();
                        String price = "" + dataSnapshot.child("productPrice").getValue();
                        String title = "" + dataSnapshot.child("productTitle").getValue();
                        String ratingId = "" + dataSnapshot.child("rating").getValue();

                        //  LatestModel Model = dataSnapshot.getValue(LatestModel.class);
                        LatestModel model = new LatestModel();
                        model.setProductTitle(title);
                        model.setProductImg(Img);
                        model.setRating(ratingId);
                        model.setCategory(category);
                        model.setProductPrice(price);
                        model.setOldPrice(oldPrice);
                        model.setFavorite(true);

                        //  latestModels.add(model);
                        database.getReference().child("Favourite").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                //  Toast.makeText(getContext(), "added successfully", Toast.LENGTH_SHORT).show();
                                 getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, cartsFragment).commit();


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                            }

                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }

}



