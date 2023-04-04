package com.example.mysoukhin.ui;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CategoryAdapter;
import com.example.mysoukhin.adapters.LatestProductsAdapter;
import com.example.mysoukhin.adapters.NewProductsAdapter;
import com.example.mysoukhin.adapters.ProductsAdapter;
import com.example.mysoukhin.models.AllCategoryModel;
import com.example.mysoukhin.models.CategoryModel;
import com.example.mysoukhin.models.FavouritesClass;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.NewProductsModel;
import com.example.mysoukhin.models.ProductsModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView cat_recycler,rec_popular,rec_latest,rec_new;
    CategoryAdapter categoryAdapter;
    NestedScrollView nestedScrollView;
    FirebaseDatabase database;
    TextView category_see_all,popularProducts_see_all,latestProducts_see_all,newProducts_see_all;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Home");

       /* ActionBar actionBar = null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);*/

        // Inflate the layout for this fragment
          View root = inflater.inflate(R.layout.fragment_home, container, false);
          ImageSlider imageSlider = root.findViewById(R.id.image_slider);
          List<SlideModel> slideModel = new ArrayList<>();
          slideModel.add(new SlideModel(R.drawable.banner1, ScaleTypes.CENTER_CROP));
          slideModel.add(new SlideModel(R.drawable.banner2, ScaleTypes.CENTER_CROP));
          slideModel.add(new SlideModel(R.drawable.banner3, ScaleTypes.CENTER_CROP));
          imageSlider.setImageList(slideModel);

          // category list
        nestedScrollView = root.findViewById(R.id.nestedScrolId);
        category_see_all = root.findViewById(R.id.category_see_all);
        cat_recycler = root.findViewById(R.id.rec_category);
        List<CategoryModel> lists = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(getActivity(),lists);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        cat_recycler.setLayoutManager(manager);
        cat_recycler.setAdapter(categoryAdapter);
        cat_recycler.setNestedScrollingEnabled(false);
        cat_recycler.setHasFixedSize(true);
        database = FirebaseDatabase.getInstance();

        lists.add(new CategoryModel(R.drawable.shirt,"T-Shirt","T_shirt"));
        lists.add(new CategoryModel(R.drawable.hoodies,"Hoodies","Hoodies"));
        lists.add(new CategoryModel(R.drawable.cap,"Cap","Cap"));
        lists.add(new CategoryModel(R.drawable.mu,"Mug","Double Mug"));
        lists.add(new CategoryModel(R.drawable.vector,"PhoneCover","Phone Cover"));
        lists.add(new CategoryModel(R.drawable.gifts,"Gift","Gifts Item"));

    category_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CategoryActivity.class);
                startActivity(intent);
            }
        });

    //popular products
        popularProducts_see_all = root.findViewById(R.id.popular_see_all);
        rec_popular = root.findViewById(R.id.rec_popular);
        List<ProductsModel> productsModels = new ArrayList<>();

        ProductsAdapter adapter  = new ProductsAdapter(getActivity(),productsModels);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rec_popular.setLayoutManager(layoutManager);
        rec_popular.setAdapter(adapter);
        rec_popular.setNestedScrollingEnabled(false);
        rec_popular.setHasFixedSize(true);

        productsModels.add(new ProductsModel(R.drawable.ab,"Cap","300","  ৳500","Cap"));
        productsModels.add(new ProductsModel(R.drawable.ac,"Phone Cover","200","  ৳500","Phone Cover"));
        productsModels.add(new ProductsModel(R.drawable.am,"Black Hoodies","1000","  ৳1500","Hoodies"));
        productsModels.add(new ProductsModel(R.drawable.akl,"Blue Hoodies","1500","  ৳2000","Hoodies"));
        productsModels.add(new ProductsModel(R.drawable.abd,"Women T_shirt","500","  ৳800","Shirt"));
        productsModels.add(new ProductsModel(R.drawable.mug,"Mug","300","  ৳500","Mug"));

        database.getReference().child("products").child("Popular products").setValue(productsModels).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
              //  Toast.makeText(getContext(), "added successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

            }
        });

        popularProducts_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ProductsShowAll.class);
                startActivity(intent);
            }
        });

        //latest products

        latestProducts_see_all = root.findViewById(R.id.latest_see_all);
        rec_latest = root.findViewById(R.id.rec_latest);
        List<LatestModel> latestModels = new ArrayList<>();

        LatestProductsAdapter latestProductsAdapter = new LatestProductsAdapter(getActivity(),latestModels);
        RecyclerView.LayoutManager layoutManagers= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rec_latest.setLayoutManager(layoutManagers);
        rec_latest.setAdapter(latestProductsAdapter);
        rec_latest.setNestedScrollingEnabled(false);
        rec_latest.setHasFixedSize(true);

        latestModels.add(new LatestModel(R.drawable.am,"(500 reviews)","Black Hoodies","1000","  ৳1500","Hoodies"));
        latestModels.add(new LatestModel(R.drawable.alm,"(450 reviews)","Yellow T_shirt","700","  ৳1000","Shirt"));
        latestModels.add(new LatestModel(R.drawable.akl,"(488 reviews)","Blue Hoodies","1000","  ৳1500","Hoodies"));
        latestModels.add(new LatestModel(R.drawable.ac,"(389 reviews)","Phone Cover","700","  ৳1500","Phone Cover"));
        latestModels.add(new LatestModel(R.drawable.mug,"(397 reviews)","Mug","300","  ৳500","Mug"));
        latestModels.add(new LatestModel(R.drawable.ac,"(356 reviews)","Phone Cover","100","  ৳300","Phone Cover"));
        latestModels.add(new LatestModel(R.drawable.abd,"(388 reviews)","Women T_shirt","300","  ৳500","Shirt"));

        database.getReference().child("products").child("Latest products").setValue(latestModels).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
              //  Toast.makeText(getContext(), "added successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();

            }
        });


        latestProducts_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ProductsShowAll.class);
                startActivity(intent);
            }
        });



        //new Products
        newProducts_see_all= root.findViewById(R.id.new_see_all);
        rec_new = root.findViewById(R.id.rec_newProduct);
        List<NewProductsModel> newProductsModels = new ArrayList<>();
        NewProductsAdapter newProductsAdapter = new NewProductsAdapter(getActivity(),newProductsModels);
        RecyclerView.LayoutManager newManager= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rec_new.setLayoutManager(newManager);
        rec_new.setAdapter(newProductsAdapter);
        rec_new.setNestedScrollingEnabled(false);
        rec_new.setHasFixedSize(true);

        newProductsModels.add(new NewProductsModel(R.drawable.abd,"(289 reviews)","Women T_shirt","500","  ৳800","Shirt"));
        newProductsModels.add(new NewProductsModel(R.drawable.mug,"(489 reviews)","Mug","300","  ৳500","Mug"));
        newProductsModels.add(new NewProductsModel(R.drawable.ab,"(389 reviews)","Cap","300","  ৳500","Cap"));
        newProductsModels.add(new NewProductsModel(R.drawable.ac,"(257 reviews)","Phone Cover","200","  ৳500","Phone Cover"));
        newProductsModels.add(new NewProductsModel(R.drawable.am,"(380 reviews)","Black Hoodies","1000","  ৳1500","Hoodies"));
        newProductsModels.add(new NewProductsModel(R.drawable.akl,"(269 reviews)","Blue Hoodies","1500","  ৳2000","Hoodies"));
        newProductsModels.add(new NewProductsModel(R.drawable.akh,"(269 reviews)","Sky Blue Hoodies","1500","  ৳2000","Hoodies"));

       database.getReference().child("products").child("New products").setValue(newProductsModels).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
              // Toast.makeText(getContext(), "added successfully", Toast.LENGTH_SHORT).show();
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
           }
       });

        newProducts_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ProductsShowAll.class);
                startActivity(intent);
            }
        });



        return root;
    }


    }


