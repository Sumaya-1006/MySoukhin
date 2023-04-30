package com.example.mysoukhin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysoukhin.R;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.NewProductsModel;
import com.example.mysoukhin.models.ProductsModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class CheckOutActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView personalInfo,payment,confirm,cashText;
    Button checkBtn,bankBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        this.setTitle("CheckOut");

        toolbar = findViewById(R.id.checkout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        seekBar = findViewById(R.id.seekbar);
        personalInfo = findViewById(R.id.personalInfo);
        payment = findViewById(R.id.payment);
        confirm = findViewById(R.id.confirm);
        cashText = findViewById(R.id.cashText);
        checkBtn = findViewById(R.id.cashId);
        bankBtn = findViewById(R.id.bankId);

        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
        seekBar.getThumb().setColorFilter(getResources().getColor(R.color.purple_500), PorterDuff.Mode.SRC_ATOP);


        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(CheckOutActivity.this, "Your ordered is processed now", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),StatusActivity.class);
                startActivity(intent);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                firebaseDatabase.getReference().child("cart").removeValue();
            }
        });
        bankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivity(intent);

            }
        });

    }
}