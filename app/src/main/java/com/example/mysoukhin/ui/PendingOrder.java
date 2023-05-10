package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.PendingAdapter;
import com.example.mysoukhin.models.PendingModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PendingOrder extends AppCompatActivity {
    RecyclerView recyclerView;
    PendingAdapter pendingAdapter;
    DatabaseReference root;
    DatabaseReference ref;
    Toolbar pToolbar;
    FirebaseAuth auth;
    String CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_order);

        pToolbar = findViewById(R.id.pending_toolbar);
        setSupportActionBar(pToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        CurrentUser = auth.getCurrentUser().getUid();

        pToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.pending_rec);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        List<PendingModel> orderModels = new ArrayList<>();

        recyclerView.setAdapter(pendingAdapter);
        ref = FirebaseDatabase.getInstance().getReference("cart").child(CurrentUser);
        root = FirebaseDatabase.getInstance().getReference();

        pendingAdapter = new PendingAdapter(this,orderModels);
        recyclerView.setAdapter(pendingAdapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PendingModel model = dataSnapshot.getValue(PendingModel.class);
                    orderModels.add(model);
                }
                pendingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}