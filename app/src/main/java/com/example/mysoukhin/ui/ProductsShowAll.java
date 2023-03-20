package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CategoryAdapter;
import com.example.mysoukhin.adapters.SeeAllAdapter;
import com.example.mysoukhin.models.CategoryModel;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.NewProductsModel;
import com.example.mysoukhin.models.ProductsModel;
import com.example.mysoukhin.models.SeeAllModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class ProductsShowAll extends AppCompatActivity {
    RecyclerView show_all_rec;
    SeeAllAdapter seeAllAdapter;
    Toolbar toolbar;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_show_all);
        this.setTitle("All Products");

        toolbar = findViewById(R.id.show_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        database = FirebaseDatabase.getInstance();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        show_all_rec = findViewById(R.id.show_all_rec);
        List<SeeAllModel> seeAllModel = new ArrayList<>();

        seeAllAdapter = new SeeAllAdapter(this,seeAllModel);
        show_all_rec.setLayoutManager(new GridLayoutManager(this,2));
        show_all_rec.setAdapter(seeAllAdapter);
        show_all_rec.setNestedScrollingEnabled(false);
        show_all_rec.setHasFixedSize(true);

        seeAllModel.add(new SeeAllModel(R.drawable.akh,"(500 reviews)","Hoodies","৳1000","  ৳1500","Hoodies"));
        seeAllModel.add(new SeeAllModel(R.drawable.alm,"(450 reviews)","Mens T_shirt","৳700","  ৳1000","T_shirt"));
        seeAllModel.add(new SeeAllModel(R.drawable.akl,"(488 reviews)","Hoodies","৳1000","  ৳1500","Hoodies"));
        seeAllModel.add(new SeeAllModel(R.drawable.ad,"(389 reviews)","Phone Cover","৳700","  ৳1500","Phone Cover"));
        seeAllModel.add(new SeeAllModel(R.drawable.mug,"(397 reviews)","Mug","৳300","  ৳500","Mug"));
        seeAllModel.add(new SeeAllModel(R.drawable.ac,"(356 reviews)","Phone Cover","৳100","  ৳300","PhoneCover"));
        seeAllModel.add(new SeeAllModel(R.drawable.abd,"(388 reviews)","Women T_shirt","৳300","  ৳500","T_shirt"));
        seeAllModel.add(new SeeAllModel(R.drawable.ab,"(389 reviews)","Cap","৳300","  ৳500","Cap"));

        database.getReference().child("products").child("showAllProducts").push().setValue(seeAllModel).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id ==R.id.searchBar){

        }
        else if (id == R.id.cartBar) {
            Intent intent = new Intent(getApplicationContext(), CartsFragment.class);
            startActivity(intent);
        }


        return true;
    }

    @Override
    public void onBackPressed() {
       // Toast.makeText(getApplicationContext(),"back button pressed",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
    }
