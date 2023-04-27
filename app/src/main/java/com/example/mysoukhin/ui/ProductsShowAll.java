package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CategoryAdapter;
import com.example.mysoukhin.adapters.SeeAllAdapter;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.models.CategoryModel;
import com.example.mysoukhin.models.FavouritesClass;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.NewProductsModel;
import com.example.mysoukhin.models.ProductsModel;
import com.example.mysoukhin.models.SeeAllModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
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
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_show_all);
        this.setTitle("Search");

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

        seeAllModel.add(new SeeAllModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FSky%20Blue%20Hoodies%20.png?alt=media&token=94d3a79f-216c-4651-9241-e645abb94881","(500 reviews)","Sky Blue Hoodies","1000",  "৳1500","Hoodies"));
        seeAllModel.add(new SeeAllModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FYellow%20T-shirt%20%20.png?alt=media&token=4fab63fa-f74d-4cef-9173-381a8ccd1747","(450 reviews)","Yellow T_shirt","700","  ৳1000","T_shirt"));
        seeAllModel.add(new SeeAllModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FBlue%20hoodies%20.png?alt=media&token=967ff854-083d-4c25-a2aa-7d000b4d6028","(488 reviews)","Blue Hoodies","1000","  ৳1500","Hoodies"));
        seeAllModel.add(new SeeAllModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","(389 reviews)","Phone Cover","700","  ৳1500","Phone Cover"));
        seeAllModel.add(new SeeAllModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FMug%20.png?alt=media&token=13d93033-4776-4ba1-81d0-383d50971896","(397 reviews)","Mug","300","  ৳500","Mug"));
        seeAllModel.add(new SeeAllModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","(356 reviews)","Phone Cover","100","  ৳300","PhoneCover"));
        seeAllModel.add(new SeeAllModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FWomen's%20T-shirt%20.png?alt=media&token=d96ebbda-648b-49ff-a9c4-a6e64d3ce529","(388 reviews)","Women T_shirt","300","  ৳500","T_shirt"));
        seeAllModel.add(new SeeAllModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FCap%20.png?alt=media&token=7c799167-3a18-466f-bd24-a0f2336623c8","(389 reviews)","Cap","300","  ৳500","Cap"));

       database.getReference().child("products").child("allProducts").setValue(seeAllModel).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
              // Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        /*if(id ==R.id.searchBar){

        }*/
         if (id == R.id.cartBar) {
             getSupportFragmentManager().beginTransaction().replace(R.id.show_layout,new CartsFragment()).commit();
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
