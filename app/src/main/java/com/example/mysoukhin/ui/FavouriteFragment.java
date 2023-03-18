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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.FavouriteAdapter;
import com.example.mysoukhin.databinding.ActivityAddProductBinding;
import com.example.mysoukhin.models.FavouritesClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FavouriteFragment extends Fragment {
    BottomNavigationView bnView;
    FloatingActionButton actionButton;
    RecyclerView fav_recycler;
    private FirebaseAuth mAuth;
    private FirebaseUser CurrentUser;
    private String UserId;
    FavouriteAdapter favouriteAdapter;
    Toolbar fToolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        getActivity().setTitle("Favourite");
       // fToolbar = view.findViewById(R.id.favourite_toolbar);

        bnView = view.findViewById(R.id.bottomNavigationView);
        actionButton = view.findViewById(R.id.actionBtn);
        fav_recycler = view.findViewById(R.id.recyclerViewId);

        mAuth = FirebaseAuth.getInstance();
        CurrentUser = mAuth.getCurrentUser();
        UserId = CurrentUser.getUid();

        Retrieve_fav();

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    loadFragment(new HomeFragment(), false);


                } else if (id == R.id.fav) {
                    loadFragment(new FavouriteFragment(), true);


                } else if (id == R.id.carts) {
                    loadFragment(new CartsFragment(), false);

                } else if (id == R.id.profile) {
                    loadFragment(new ProfileFragment(), false);

                }

                return true;
            }
        });
        ColorStateList iconColorStates = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{
                        Color.parseColor("#9A9998"),
                        Color.parseColor("#553DF2")
                });

        bnView.setItemIconTintList(iconColorStates);


        return view;
    }
    public void loadFragment (Fragment fragment, boolean flag ) {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_layout, fragment);
        ft.commit();
    }

    public void Retrieve_fav() {

        // rc.setHasFixedSize(true);
        // rc.setLayoutManager(new LinearLayoutManager(this));

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        fav_recycler.setLayoutManager(mGridLayoutManager);
        final List<FavouritesClass> favourite_list = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("favourites")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    FavouritesClass fav = new FavouritesClass();
                    fav = ds.getValue(FavouritesClass.class);
                    favourite_list.add(fav);
                }
                favouriteAdapter = new FavouriteAdapter(getContext(), favourite_list);
                fav_recycler.setAdapter(favouriteAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        ref.addListenerForSingleValueEvent(eventListener);
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


}


