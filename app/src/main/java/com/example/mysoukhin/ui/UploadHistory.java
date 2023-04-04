package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.AddressAdapter;
import com.example.mysoukhin.adapters.UploadAdapter;
import com.example.mysoukhin.models.AddressModel;
import com.example.mysoukhin.models.UploadModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UploadHistory extends AppCompatActivity {
    UploadAdapter adapter;
    RecyclerView upload_rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_history);
        this.setTitle("Upload History");

        upload_rec = findViewById(R.id.upload_rec);

        List<UploadModel> model = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new UploadAdapter(this, model);
        LinearLayoutManager uManager = new LinearLayoutManager(this);
        upload_rec.setLayoutManager(uManager);
        upload_rec.addItemDecoration(new DividerItemDecoration(upload_rec.getContext(),DividerItemDecoration.VERTICAL ));
        upload_rec.setAdapter(adapter);
        upload_rec.setNestedScrollingEnabled(false);
        upload_rec.setHasFixedSize(true);

        database.getReference().child("uploads").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UploadModel model1 = dataSnapshot.getValue(UploadModel.class);
                    model1.getImg().toString().trim();
                   // model1.getProduct_name().toString().trim();
                    model1.getProduct_type().toString().trim();
                    model.add(model1);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}