package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.AllCategoryAdapter;
import com.example.mysoukhin.models.AllCategoryModel;
import com.example.mysoukhin.models.SeeAllModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategoryCap extends AppCompatActivity {

    Toolbar capToolbar;
    FirebaseDatabase database;
    RecyclerView capRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_cap);
        this.setTitle("Cap");

        capToolbar = findViewById(R.id.cap_toolbar);
        setSupportActionBar(capToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        database = FirebaseDatabase.getInstance();

        capToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        capRecycler = findViewById(R.id.cap_rec);
        List<AllCategoryModel> models = new ArrayList<>();
        AllCategoryAdapter adapters = new AllCategoryAdapter(this, models);
        capRecycler.setLayoutManager(new GridLayoutManager(this,2));
        capRecycler.setAdapter(adapters);
        capRecycler.setNestedScrollingEnabled(false);
        capRecycler.setHasFixedSize(true);

        database = FirebaseDatabase.getInstance();

        models.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FCap%20.png?alt=media&token=7c799167-3a18-466f-bd24-a0f2336623c8","Cap","৳300","  ৳500","Cap"));
        models.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FCap%20.png?alt=media&token=7c799167-3a18-466f-bd24-a0f2336623c8","Cap","৳300","  ৳500","Cap"));
        models.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FCap%20.png?alt=media&token=7c799167-3a18-466f-bd24-a0f2336623c8","Cap","৳300","  ৳500","Cap"));
        models.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FCap%20.png?alt=media&token=7c799167-3a18-466f-bd24-a0f2336623c8","Cap","৳300","  ৳500","Cap"));
        models.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FCap%20.png?alt=media&token=7c799167-3a18-466f-bd24-a0f2336623c8","Cap","৳300","  ৳500","Cap"));
        models.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FCap%20.png?alt=media&token=7c799167-3a18-466f-bd24-a0f2336623c8","Cap","৳300","  ৳500","Cap"));

        database.getReference().child("products").child("all cap").setValue(models).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
              //  Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.searchBar) {

        } else if (id == R.id.cartBar) {
            Intent intent = new Intent(getApplicationContext(), CartsFragment.class);
            startActivity(intent);
        }
        return true;
    }

}
