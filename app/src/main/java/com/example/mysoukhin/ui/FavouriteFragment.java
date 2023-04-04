package com.example.mysoukhin.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CartAdapter;
import com.example.mysoukhin.adapters.FavouriteAdapter;
import com.example.mysoukhin.databinding.ActivityAddProductBinding;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.models.FavouritesClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {
    RecyclerView fav_recycler;
    private FirebaseAuth mAuth;
    private FirebaseUser CurrentUser;
    private String UserId;
    public ArrayList<FavouritesClass> favouritesClasses;
    FavouriteAdapter favouriteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        getActivity().setTitle("Favourite");
        // fToolbar = view.findViewById(R.id.favourite_toolbar);
        fav_recycler = view.findViewById(R.id.recyclerViewId);

        mAuth = FirebaseAuth.getInstance();
        CurrentUser = mAuth.getCurrentUser();
        UserId = CurrentUser.getUid();
        Retrieve_fav();

        return view;
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id ==R.id.searchBar){

        }
        else if (id == R.id.cartBar) {
            Intent intent = new Intent(getContext(), CartsFragment.class);
            startActivity(intent);
        }


        return true;
    }

    private void Retrieve_fav() {
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        fav_recycler.setLayoutManager(mGridLayoutManager);
        favouritesClasses = new ArrayList<>();
        favouriteAdapter = new FavouriteAdapter(getContext(), favouritesClasses);
        fav_recycler.setAdapter(favouriteAdapter);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        firebaseDatabase.getReference().child("Favourite").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                  /*  FavouritesClass favourites= dataSnapshot.getValue(FavouritesClass.class);
                    favourites.getProductTitle().toString().trim();
                    favourites.getProductPrice().toString().trim();
                    favourites.getProductOldPrice().toString().trim();
                    favourites.isChecked();
                    favourites.getProductImage().toString().trim();

                    favouritesClasses.add(favourites);*/
                }
                favouriteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }


