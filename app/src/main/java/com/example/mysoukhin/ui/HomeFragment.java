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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CategoryAdapter;
import com.example.mysoukhin.adapters.DesignAdapter;
import com.example.mysoukhin.adapters.LatestProductsAdapter;
import com.example.mysoukhin.adapters.NewProductsAdapter;
import com.example.mysoukhin.adapters.ProductsAdapter;
import com.example.mysoukhin.adapters.UploadAdapter;
import com.example.mysoukhin.models.AllCategoryModel;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.models.CategoryModel;
import com.example.mysoukhin.models.FavouritesClass;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.NewProductsModel;
import com.example.mysoukhin.models.ProductsModel;
import com.example.mysoukhin.models.UploadModel;
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

    RecyclerView cat_recycler,rec_popular,rec_latest,rec_new,rec_design;
    CategoryAdapter categoryAdapter;
    NestedScrollView nestedScrollView;
    FirebaseDatabase database;
    private static List<FavouritesClass> favourites;
    TextView category_see_all,popularProducts_see_all,latestProducts_see_all,newProducts_see_all;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Home");

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

        productsModels.add(new ProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FCap%20.png?alt=media&token=7c799167-3a18-466f-bd24-a0f2336623c8","Cap","300","  ৳500","Cap"));
        productsModels.add(new ProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","Phone Cover","200","  ৳500","Phone Cover"));
        productsModels.add(new ProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FBlack%20hoodies%20.png?alt=media&token=aa1011ea-cd8b-4c18-b792-4fa9214e3aad","Black Hoodies","1000","  ৳1500","Hoodies"));
        productsModels.add(new ProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FBlue%20hoodies%20.png?alt=media&token=967ff854-083d-4c25-a2aa-7d000b4d6028","Blue Hoodies","1500","  ৳2000","Hoodies"));
        productsModels.add(new ProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FWomen%20t-shirt%20%20.png?alt=media&token=bef29f4d-19a1-42fe-bfce-42c1093bfd91","Women T_shirt","500","  ৳800","Shirt"));
        productsModels.add(new ProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FMug%20.png?alt=media&token=13d93033-4776-4ba1-81d0-383d50971896","Mug","300","  ৳500","Mug"));

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

        latestModels.add(new LatestModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FBlack%20hoodies%20.png?alt=media&token=aa1011ea-cd8b-4c18-b792-4fa9214e3aad","(500 reviews)","Black Hoodies","1000","  ৳1500","Hoodies"));
        latestModels.add(new LatestModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FYellow%20T-shirt%20%20.png?alt=media&token=4fab63fa-f74d-4cef-9173-381a8ccd1747","(450 reviews)","Yellow T_shirt","700","  ৳1000","Shirt"));
        latestModels.add(new LatestModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FBlue%20hoodies%20.png?alt=media&token=967ff854-083d-4c25-a2aa-7d000b4d6028","(488 reviews)","Blue Hoodies","1000","  ৳1500","Hoodies"));
        latestModels.add(new LatestModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","(389 reviews)","Phone Cover","700","  ৳1500","Phone Cover"));
        latestModels.add(new LatestModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FMug%20.png?alt=media&token=13d93033-4776-4ba1-81d0-383d50971896","(397 reviews)","Mug","300","  ৳500","Mug"));
        latestModels.add(new LatestModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","(356 reviews)","Phone Cover","100","  ৳300","Phone Cover"));
        latestModels.add(new LatestModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FWomen%20t-shirt%20%20.png?alt=media&token=bef29f4d-19a1-42fe-bfce-42c1093bfd91","(388 reviews)","Women T_shirt","300","  ৳500","Shirt"));

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

        newProductsModels.add(new NewProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FWomen%20t-shirt%20%20.png?alt=media&token=bef29f4d-19a1-42fe-bfce-42c1093bfd91","(289 reviews)","Women T_shirt","500","  ৳800","Shirt"));
        newProductsModels.add(new NewProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FMug%20.png?alt=media&token=13d93033-4776-4ba1-81d0-383d50971896","(489 reviews)","Mug","300","  ৳500","Mug"));
        newProductsModels.add(new NewProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FCap%20.png?alt=media&token=7c799167-3a18-466f-bd24-a0f2336623c8","(389 reviews)","Cap","300","  ৳500","Cap"));
        newProductsModels.add(new NewProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FPhone%20Cover%20.png?alt=media&token=5e4d1126-a8c1-4841-9a03-27107df4e867","(257 reviews)","Phone Cover","200","  ৳500","Phone Cover"));
        newProductsModels.add(new NewProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FBlack%20hoodies%20.png?alt=media&token=aa1011ea-cd8b-4c18-b792-4fa9214e3aad","(380 reviews)","Black Hoodies","1000","  ৳1500","Hoodies"));
        newProductsModels.add(new NewProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FBlue%20hoodies%20.png?alt=media&token=967ff854-083d-4c25-a2aa-7d000b4d6028","(269 reviews)","Blue Hoodies","1500","  ৳2000","Hoodies"));
        newProductsModels.add(new NewProductsModel("https://firebasestorage.googleapis.com/v0/b/mysoukhin.appspot.com/o/uploads%2FSky%20Blue%20Hoodies%20.png?alt=media&token=94d3a79f-216c-4651-9241-e645abb94881","(269 reviews)","Sky Blue Hoodies","1500","  ৳2000","Hoodies"));

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

        // design
        rec_design = root.findViewById(R.id.rec_design);

        List<UploadModel> uploadModels = new ArrayList<>();
        DesignAdapter designAdapter = new DesignAdapter(getActivity(),uploadModels);
        RecyclerView.LayoutManager designManager= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rec_design.setLayoutManager(designManager);
        rec_design.setAdapter(designAdapter);
        rec_design.setNestedScrollingEnabled(false);
        rec_design.setHasFixedSize(true);

        database.getReference().child("uploads").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UploadModel model = dataSnapshot.getValue(UploadModel.class);
                    model.getImg().toString().trim();
                    model.getProduct_name().toString().trim();
                    uploadModels.add(model);
                }
                designAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }


    }


