package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class CategoryPhone extends AppCompatActivity {
    Toolbar coverToolbar;
    FirebaseDatabase database;
    RecyclerView cover_rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_phone);
        this.setTitle("Phone Cover");

        coverToolbar = findViewById(R.id.cover_toolBar);
        setSupportActionBar(coverToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        database = FirebaseDatabase.getInstance();
        cover_rec = findViewById(R.id.cover_rec);

        coverToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        List<AllCategoryModel> model = new ArrayList<>();
        AllCategoryAdapter adapters = new AllCategoryAdapter(this, model );
        cover_rec.setLayoutManager(new GridLayoutManager(this, 2));
        cover_rec.setAdapter(adapters);
        cover_rec.setNestedScrollingEnabled(false);
        cover_rec.setHasFixedSize(true);

        model.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","Phone Cover","৳200","  ৳300","Phone Cover"));
        model.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","Phone Cover","৳200","  ৳300","Phone Cover"));
        model.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","Phone Cover","৳200","  ৳300","Phone Cover"));
        model.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","Phone Cover","৳200","  ৳300","Phone Cover"));
        model.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","Phone Cover","৳200","  ৳300","Phone Cover"));
        model.add(new AllCategoryModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","Phone Cover","৳200","  ৳300","Phone Cover"));

        database.getReference().child("products").child("phone cover").setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
               // Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
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

