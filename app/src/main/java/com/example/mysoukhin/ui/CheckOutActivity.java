package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.HistoryModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CheckOutActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView personalInfo, payment, confirm, cashText;
    Button checkBtn, bankBtn;
    Toolbar toolbar;
    DatabaseReference root;
    String ProductName = "";
    ProgressDialog progressDialog;
    ArrayList<HistoryModel> models;
    FirebaseAuth auth;
    String CurrentUser;

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
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        CurrentUser = auth.getCurrentUser().getUid();

        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
        seekBar.getThumb().setColorFilter(getResources().getColor(R.color.purple_500), PorterDuff.Mode.SRC_ATOP);

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user != null) {
                    // user logged in
                    Intent intent = new Intent(getApplicationContext(), OrderHistory.class);
                    intent.putExtra("productTitle",ProductName);
                    startActivity(intent);
                    savedData();

                }else{
                    Toast.makeText(getApplicationContext(), "Please first create your account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        bankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                startActivity(intent);

            }
        });

    }

   private void savedData() {

       root = FirebaseDatabase.getInstance().getReference();

       auth = FirebaseAuth.getInstance();
       CurrentUser = auth.getCurrentUser().getUid();
       DatabaseReference x = root.child("cart").child(CurrentUser);
       x.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               FirebaseDatabase t = FirebaseDatabase.getInstance();
               String key  = t.getReference("order").push().getKey();
               root.child("order").child(CurrentUser).child(key).child("orderproducts").setValue(snapshot.getValue());
               root.child("order").child(CurrentUser).child(key).child("orderproducts").child("totalPrice").removeValue();
               root.child("order").child(CurrentUser).child(key).child("Date").setValue(String.valueOf(new SimpleDateFormat("dd MMM yyyy hh:mm a").format(Calendar.getInstance().getTime())));
               root.child("order").child(CurrentUser).child(key).child("IsChecked").setValue("false");
               Toast.makeText( getApplicationContext() ,"Confirmed Completed" , Toast.LENGTH_LONG).show();
               startActivity(new Intent(getApplicationContext(), OrderHistory.class));
               root.child("cart").child(CurrentUser).removeValue();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
      /* progressDialog.setMessage("Placing order");
        progressDialog.show();;

        String timestamp = ""+System.currentTimeMillis();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("orderId",timestamp);
        hashMap.put("orderTime",timestamp);
        hashMap.put("orderBy",FirebaseAuth.getInstance().getUid());
        hashMap.put("orderStatus","In progress");

        root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference x = root.child("cart").child(ProductName);
        x.child(timestamp).child("order list").setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                for(int i=0; i<models.size();i++){
                    String id = models.get(i).toString();
                    String productPrice = models.get(i).toString();
                    String quantity = models.get(i).toString();
                    String productTitle = models.get(i).toString();
                    HashMap<String,Object> hashMap1 = new HashMap<>();

                    hashMap1.put("Id",id);
                    hashMap1.put("productPrice",productPrice);
                    hashMap1.put("quantity",quantity);
                    hashMap1.put("productTitle",productTitle);
                    x.child(timestamp).child("items").setValue(hashMap1);


                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });*/
   }

    }

