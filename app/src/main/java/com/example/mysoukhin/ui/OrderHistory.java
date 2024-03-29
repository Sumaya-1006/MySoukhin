package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        FirebaseAuth auth;
        String CurrentUser;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_order_history);
            oToolbar = findViewById(R.id.order_toolbar);
            setSupportActionBar(oToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

          /*  mAuth = FirebaseAuth.getInstance();
            CurrentUsr = mAuth.getCurrentUser();
            UserId = CurrentUsr.getUid();*/

            auth = FirebaseAuth.getInstance();
            CurrentUser = auth.getCurrentUser().getUid();

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
            historyAdapter = new HistoryAdapter(this,historyModels);
            recyclerView.setAdapter(historyAdapter);

            DatabaseReference roott= FirebaseDatabase.getInstance().getReference();
            DatabaseReference x = roott.child("order").child(CurrentUser);
            ValueEventListener valueEventListener1 =new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String Date = dataSnapshot.child("Date").getValue().toString();
                            int nums = ((int)(dataSnapshot.child("orderproducts").getChildrenCount()));
                            String OrderCheck = dataSnapshot.child("IsChecked").getValue().toString();

                            String products="Products :\n";
                            for (DataSnapshot data : dataSnapshot.child("orderproducts").getChildren())
                            {
                                products+= "    #"+data.getKey() + "\n        Price: " + data.child("productPrice").getValue().toString() + " ৳\n        Quantity: " + data.child("quantity").getValue().toString()+"\n";
                            }

                            historyModels.add( new HistoryModel(dataSnapshot.getKey(),"   Date :  " + Date ,"   Products Number :  "+String.valueOf(nums), "   "+products,OrderCheck));
                        }
                    }
                    else{
                        historyModels.clear();
                    }
                    historyAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            };
            x.addListenerForSingleValueEvent(valueEventListener1);

        }
    }
