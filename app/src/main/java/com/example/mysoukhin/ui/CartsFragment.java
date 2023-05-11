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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CartAdapter;
import com.example.mysoukhin.models.CartItemModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CartsFragment extends Fragment {
    public ArrayList<CartItemModel> cartItemModelList;
    CartAdapter cartAdapter;
    private RecyclerView CartItemRecyclerView;
    DatabaseReference ref;
    TextView rec_amount;
    Button rec_check;
    FirebaseAuth auth;
    String CurrentUser;

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

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        CurrentUser = auth.getCurrentUser().getUid();

        ref = FirebaseDatabase.getInstance().getReference("cart").child(CurrentUser);
        rec_amount = view.findViewById(R.id.rec_amount);
        rec_check = view.findViewById(R.id.rec_checkout);

        //receive data
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReceiver,new IntentFilter("MyTotalAmount"));

        cartAdapter = new CartAdapter(getContext(), cartItemModelList);
        CartItemRecyclerView.setAdapter(cartAdapter);
        String timestamp = ""+System.currentTimeMillis();

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
                //savedData();
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
                if(account !=null){
                    Intent intent = new Intent(getContext(), AddressActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getContext(), "Please first create your account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);

                }

                  /*if (user != null) {
                    // user logged in



                }else{
                    Toast.makeText(getContext(), "Please first create your account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }*/
            }
        });

        return view;
    }

    /*private void savedData() {
        root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference x = root.child("cart");
        x.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                root.child("order").push().setValue(snapshot.getValue()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText( getContext() ,"Successfully added" , Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/


    public BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String totalBill = String.valueOf((intent.getIntExtra("total_amount",0)));
            Log.e("error tag", String.valueOf(totalBill));
            rec_amount.setText("Total Amount :" +totalBill+" ৳");

        }
    };
}