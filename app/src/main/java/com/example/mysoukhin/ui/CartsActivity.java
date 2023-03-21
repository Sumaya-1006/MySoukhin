package com.example.mysoukhin.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mysoukhin.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RelativeLayout CustomCartContainer;
    private TextView PageTitle;
    public static Activity fa;
    private String ProductName, ProductPrice, ProductImage, ProductIsFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);

        fa=this;

        mToolbar = findViewById(R.id.cartToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("My Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // sending data
        ProductName= getIntent().getStringExtra("Product Name");
        ProductPrice = getIntent().getStringExtra("Product Price");
        ProductImage = getIntent().getStringExtra("Product Image");
        ProductIsFavorite= getIntent().getStringExtra("Product IsFavorite");

        getSupportFragmentManager().beginTransaction().replace(R.id.Cartframe,new CartsFragment()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        showCartIcon();
    }


    private void showCartIcon() {

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_toolbar,null);
        CustomCartContainer = findViewById(R.id.CustomCartIconContainer);
        PageTitle = findViewById(R.id.PageTitle);
        PageTitle.setVisibility(View.GONE);
        CustomCartContainer.setVisibility(View.GONE);

    }
}