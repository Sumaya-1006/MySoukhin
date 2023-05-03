package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckOutActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView personalInfo,payment,confirm,cashText;
    Button checkBtn,bankBtn;
    Toolbar toolbar;
    DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        this.setTitle("CheckOut");

        toolbar = findViewById(R.id.checkout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
                Intent intent = new Intent(getApplicationContext(),OrderHistory.class);
                startActivity(intent);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                savedData();

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

    private void savedData() {

            root = FirebaseDatabase.getInstance().getReference();
            DatabaseReference x = root.child("cart");
            x.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    root.child("order").setValue(snapshot.getValue()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText( getApplicationContext() ,"Successfully added" , Toast.LENGTH_LONG).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    root.child("cart").removeValue();
               /* root.child("order").child("productImg").setValue(snapshot.getValue());
                root.child("order").child("productTitle").setValue(snapshot.getValue());
                root.child("order").child("Date").setValue(String.valueOf(new SimpleDateFormat("dd MMM yyyy hh:mm a").format(Calendar.getInstance().getTime())));
                Toast.makeText( getContext() ,"Confirmed Completed" , Toast.LENGTH_LONG).show();
                root.child("cart").removeValue();*/
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }
}