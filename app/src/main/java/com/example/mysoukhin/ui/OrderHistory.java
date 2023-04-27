package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CartAdapter;
import com.example.mysoukhin.adapters.OrderAdapter;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.models.OrderModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    DatabaseReference root;
    DatabaseReference ref;
    ImageView orderBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        recyclerView = findViewById(R.id.order_rec);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        List<OrderModel> orderModels = new ArrayList<>();

        recyclerView.setAdapter(orderAdapter);
        ref = FirebaseDatabase.getInstance().getReference("cart");
        root = FirebaseDatabase.getInstance().getReference();

        orderAdapter = new OrderAdapter(this,orderModels);
        recyclerView.setAdapter(orderAdapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   OrderModel model = dataSnapshot.getValue(OrderModel.class);
                    orderModels.add(model);
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}