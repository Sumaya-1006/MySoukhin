package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysoukhin.R;
import com.example.mysoukhin.models.AddressModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  ShippingAddressActivity extends AppCompatActivity {
    Button saveBtn;
    EditText name,phoneNum,address;
    FirebaseDatabase database;
    Toolbar toolbar;
    FirebaseAuth auth;
    String CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);
        this.setTitle("Address");
        toolbar = findViewById(R.id.shipping_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        auth = FirebaseAuth.getInstance();
        CurrentUser = auth.getCurrentUser().getUid();

        saveBtn = findViewById(R.id.saveBtn);
        name = findViewById(R.id.nameEditText);
        phoneNum= findViewById(R.id.phoneEditText);
        address = findViewById(R.id.deliveryEditText);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(),AddressActivity.class);
                startActivity(intent);

                getUserProfileData();

            }
        });
    }

    private void getUserProfileData() {
        AddressModel models = new AddressModel();
        models.setName(name.getText().toString());
        models.setAddress(address.getText().toString());
        models.setPhoneNum(phoneNum.getText().toString());
        database = FirebaseDatabase.getInstance();

        database.getReference().child("address").child(CurrentUser).push().setValue(models).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ShippingAddressActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ShippingAddressActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}