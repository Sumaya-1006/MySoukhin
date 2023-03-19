package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AllHoodies extends AppCompatActivity {
    Toolbar hToolbar;
    FirebaseDatabase database;
    RecyclerView hoodies_rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hoodies);

        this.setTitle("All Hoodies");

        hToolbar = findViewById(R.id.hoodies_toolBar);
        setSupportActionBar(hToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        database = FirebaseDatabase.getInstance();
        hoodies_rec = findViewById(R.id.hoodies_rec);

        hToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        List<AllCategoryModel> allCategoryModels = new ArrayList<>();
        AllCategoryAdapter adapter = new AllCategoryAdapter(this, allCategoryModels);
        hoodies_rec.setLayoutManager(new GridLayoutManager(this, 2));
        hoodies_rec.setAdapter(adapter);
        hoodies_rec.setNestedScrollingEnabled(false);
        hoodies_rec.setHasFixedSize(true);

        allCategoryModels.add(new AllCategoryModel(R.drawable.akh, "Hoodies", "৳1500", " ৳1000", "Hoodies"));
        allCategoryModels.add(new AllCategoryModel(R.drawable.akl, "Hoodies", "৳1500", " ৳1000", "Hoodies"));
        allCategoryModels.add(new AllCategoryModel(R.drawable.akl, "Hoodies", "৳1500", " ৳1000", "Hoodies"));
        allCategoryModels.add(new AllCategoryModel(R.drawable.akh, "Hoodies", "৳1500", " ৳1000", "Hoodies"));
        allCategoryModels.add(new AllCategoryModel(R.drawable.akl, "Hoodies", "৳1500", " ৳1000", "Hoodies"));
        allCategoryModels.add(new AllCategoryModel(R.drawable.akh, "Hoodies", "৳1500", " ৳1000", "Hoodies"));

        database.getReference().child("AllHoodies").push().setValue(allCategoryModels).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
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