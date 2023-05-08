package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.HistoryAdapter;
import com.example.mysoukhin.models.HistoryModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    DatabaseReference ref;
    Toolbar oToolbar;
    FirebaseAuth mAuth;
    private FirebaseUser CurrentUsr;
    private String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        oToolbar = findViewById(R.id.order_toolbar);
        setSupportActionBar(oToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        CurrentUsr = mAuth.getCurrentUser();
        UserId = CurrentUsr.getUid();
        getIntent().getStringExtra("productTitle");

        oToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StatusActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.order_rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        List<HistoryModel> historyModels = new ArrayList<>();
        recyclerView.setAdapter(historyAdapter);
        String timestamp = ""+System.currentTimeMillis();
        ref = FirebaseDatabase.getInstance().getReference("Order");

        historyAdapter = new HistoryAdapter(this,historyModels);
        recyclerView.setAdapter(historyAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseDatabase t = FirebaseDatabase.getInstance();

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                       String Date = dataSnapshot.child("Date").getValue().toString();
                        int nums = ((int) (dataSnapshot.child("orderproducts").getChildrenCount()));
                        String OrderCheck = dataSnapshot.child("IsChecked").getValue().toString();
                        String products="Products :\n";
                        for (DataSnapshot data : dataSnapshot.child("orderproducts").getChildren())
                        {
                         // products+= "Price :"+data.child("productPrice").getValue()+ " \n " +data.child("productTitle").getValue()+"\n"+data.child("quantity");
                            products+= "    #"+data.getKey() + "\n        Price: " + data.child("productPrice").getValue().toString() + " TK\n        Quantity: " + data.child("quantity").getValue().toString()+"\n";

                        }

                        historyModels.add( new HistoryModel(dataSnapshot.getKey(),"   Date :  " + Date ,"   Products Number :  "+String.valueOf(nums), OrderCheck,products));
                    }
                }
                else{
                    historyModels.clear();
                }
                historyAdapter.notifyDataSetChanged();


                    }


              /*  if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            Log.d("key", dataSnapshot.getKey());
                            HistoryModel model = dataSnapshot.getValue(HistoryModel.class);
                            historyModels.add(model);



                        }
                    }
                    historyAdapter.notifyDataSetChanged();

                }
            */

                @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
    }

