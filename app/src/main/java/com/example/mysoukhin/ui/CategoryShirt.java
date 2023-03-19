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

public class CategoryShirt extends AppCompatActivity {
    Toolbar sToolbar;
    FirebaseDatabase database;
    RecyclerView shirt_rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_shirt);
        this.setTitle("T_shirt");

        sToolbar = findViewById(R.id.shirt_toolBar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        database = FirebaseDatabase.getInstance();
        shirt_rec = findViewById(R.id.shirt_rec);

        sToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        List<AllCategoryModel> allCategoryModels = new ArrayList<>();
        AllCategoryAdapter adapter = new AllCategoryAdapter(this, allCategoryModels);
        shirt_rec.setLayoutManager(new GridLayoutManager(this, 2));
        shirt_rec.setAdapter(adapter);
        shirt_rec.setNestedScrollingEnabled(false);
        shirt_rec.setHasFixedSize(true);

        allCategoryModels.add(new AllCategoryModel(R.drawable.alm,"Mens T_shirt","৳700","  ৳1000","T_shirt"));
        allCategoryModels.add(new AllCategoryModel(R.drawable.abd,"Women T_shirt","৳300","  ৳500","T_shirt"));
        allCategoryModels.add(new AllCategoryModel(R.drawable.alm,"Mens T_shirt","৳700","  ৳1000","T_shirt"));
        allCategoryModels.add(new AllCategoryModel(R.drawable.abd,"Women T_shirt","৳300","  ৳500","T_shirt"));
        allCategoryModels.add(new AllCategoryModel(R.drawable.alm,"Mens T_shirt","৳700","  ৳1000","T_shirt"));
        allCategoryModels.add(new AllCategoryModel(R.drawable.abd,"Women T_shirt","৳300","  ৳500","T_shirt"));

        database.getReference().child("AllShirt").push().setValue(allCategoryModels).addOnSuccessListener(new OnSuccessListener<Void>() {
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

