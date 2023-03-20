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
    public int totalpriceVal = 0;
    private String CurrentUser;
    public static TextView totalprice;

    public CartsFragment() {

    }

    public static CartsFragment newInstance(String param1, String param2) {
        CartsFragment fragment = new CartsFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mAuth=FirebaseAuth.getInstance();
        CurrentUser = mAuth.getCurrentUser().getUid();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carts, container, false);
        getActivity().setTitle("Carts");


        CartItemRecyclerView = view.findViewById(R.id.cart_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        CartItemRecyclerView.setLayoutManager(layoutManager);
        cartItemModelList = new ArrayList<>();
        cartAdapter = new CartAdapter(getContext(), cartItemModelList);
        CartItemRecyclerView.setAdapter(cartAdapter);

       /*cartItemModelList.add(new CartItemModel(R.drawable.ab, "Cap", 600, 2));
        cartItemModelList.add(new CartItemModel(R.drawable.abd, "Shirt", 1500, 3));
        cartItemModelList.add(new CartItemModel(R.drawable.akh, "Hoodie", 1600, 2));
        cartItemModelList.add(new CartItemModel(R.drawable.akl, "Hoodie", 1600, 2));
*/
        root = FirebaseDatabase.getInstance().getReference();
        m = root.child("cart");
        cartAdapter = new CartAdapter(getContext(), cartItemModelList);
        CartItemRecyclerView.setAdapter(cartAdapter);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.child("latestProducts").getChildren()) {
                            Log.d("key", dataSnapshot.getKey());
                            String cartItemTitle = dataSnapshot.child("productTitle").getValue(String.class).toString();;
                            String cartItemImage = dataSnapshot.child("productImg").getValue(String.class).toString();
                            String cartItemPrice = dataSnapshot.child("productPrice").getValue(String.class).toString();
                            String quantity = dataSnapshot.child("quantity").getValue(String.class).toString();
                            cartItemModelList.add(new CartItemModel(Integer.parseInt( cartItemImage), cartItemTitle, Integer.parseInt(cartItemPrice), Integer.parseInt(quantity)));
                        }

                    }
                    accountTotalPrice();
                    cartAdapter.notifyDataSetChanged();
                }
                @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        m.addListenerForSingleValueEvent(valueEventListener);


        return view;
    }

    private void accountTotalPrice() {

       totalpriceVal = 0;
        m = root.child("cart");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.child("latestProducts").getChildren()) {

                        if (!dataSnapshot.getKey().equals("price")) {

                            String cartItemPrice = dataSnapshot.child("price").getValue(String.class).toString();
                            String quantity = dataSnapshot.child("quantity").getValue(String.class).toString();
                            totalpriceVal += Integer.parseInt(cartItemPrice) * Integer.parseInt(quantity);
                        }

                    }
                    root.child("cart").child("latestProducts").child("price").setValue(String.valueOf(totalpriceVal));

                    totalprice.setText(String.valueOf(totalpriceVal) + " TK");
                    cartAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

    }
}
