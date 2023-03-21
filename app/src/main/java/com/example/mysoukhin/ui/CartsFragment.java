package com.example.mysoukhin.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CartAdapter;
import com.example.mysoukhin.models.AddressModel;
import com.example.mysoukhin.models.CartItemModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class CartsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public ArrayList<CartItemModel> cartItemModelList;
    CartAdapter cartAdapter;
    private RecyclerView CartItemRecyclerView;
    DatabaseReference root;
    DatabaseReference m;
    private FirebaseAuth mAuth;



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
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


       /* cartItemModelList.add(new CartItemModel(R.drawable.ab, "Cap", 600, 2));
        cartItemModelList.add(new CartItemModel(R.drawable.abd, "Shirt", 1500, 3));
        cartItemModelList.add(new CartItemModel(R.drawable.akh, "Hoodie", 1600, 2));
        cartItemModelList.add(new CartItemModel(R.drawable.akl, "Hoodie", 1600, 2));*/

        root = FirebaseDatabase.getInstance().getReference();
        m = root.child("carts");
        cartAdapter = new CartAdapter(getContext(), cartItemModelList);
        CartItemRecyclerView.setAdapter(cartAdapter);

        firebaseDatabase.getReference().child("carts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CartItemModel model = dataSnapshot.getValue(CartItemModel.class);
                    cartItemModelList.add(model);
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
    }


