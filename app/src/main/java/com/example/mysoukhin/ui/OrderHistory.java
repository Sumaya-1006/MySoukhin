package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CartAdapter;
import com.example.mysoukhin.adapters.HistoryAdapter;
import com.example.mysoukhin.adapters.OrderAdapter;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.models.HistoryModel;
import com.example.mysoukhin.models.OrderModel;
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
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        oToolbar = findViewById(R.id.order_toolbar);
        setSupportActionBar(oToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        ref = FirebaseDatabase.getInstance().getReference("order");

        historyAdapter = new HistoryAdapter(this,historyModels);
        recyclerView.setAdapter(historyAdapter);

       ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                HistoryModel model = dataSnapshot.getValue(HistoryModel.class);
                                historyModels.add(model);
                            }
                            historyAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
    }
