package com.example.mysoukhin.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CartAdapter;
import com.example.mysoukhin.models.CartItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class CartsFragment extends Fragment {
    public ArrayList<CartItemModel> cartItemModelList;
    CartAdapter cartAdapter;
    private RecyclerView CartItemRecyclerView;
    DatabaseReference root;
    DatabaseReference ref;
    TextView rec_amount;
    Button rec_check;

    public CartsFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carts, container, false);
        getActivity().setTitle("My Carts");
        CartItemRecyclerView = view.findViewById(R.id.cart_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        CartItemRecyclerView.setLayoutManager(layoutManager);
        cartItemModelList = new ArrayList<>();
        cartAdapter = new CartAdapter(getContext(), cartItemModelList);
        CartItemRecyclerView.setAdapter(cartAdapter);
        ref = FirebaseDatabase.getInstance().getReference("cart");
        root = FirebaseDatabase.getInstance().getReference("cart");

        rec_amount = view.findViewById(R.id.rec_amount);
        rec_check = view.findViewById(R.id.rec_checkout);

        //receive data
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReceiver,new IntentFilter("MyTotalAmount"));

        cartAdapter = new CartAdapter(getContext(), cartItemModelList);
        CartItemRecyclerView.setAdapter(cartAdapter);

        ref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {

               for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   CartItemModel itemModel = dataSnapshot.getValue(CartItemModel.class);
                   cartItemModelList.add(itemModel);
               }
               cartAdapter.notifyDataSetChanged();
               //accountTotalPrice();

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
        rec_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShippingAddressActivity.class);
                startActivity(intent);
            }
        });

        return view;


    }

   public BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String totalBill = String.valueOf((intent.getIntExtra("total_amount",0)));
            Log.e("error tag", String.valueOf(totalBill));
            rec_amount.setText("Total Amount :" +totalBill+" ৳");

        }
    };


}