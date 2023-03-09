package com.example.mysoukhin.ui;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.example.mysoukhin.models.CategoryModel;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.NewProductsModel;
import com.example.mysoukhin.models.ProductsModel;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView cat_recycler,rec_popular,rec_latest,rec_new;
    CategoryAdapter categoryAdapter;
    NestedScrollView nestedScrollView;
    TextView category_see_all,popularProducts_see_all,latestProducts_see_all,newProducts_see_all;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        lists.add(new CategoryModel(R.drawable.t,"T-Shirt"));
        lists.add(new CategoryModel(R.drawable.grup,"Hoodies"));
        lists.add(new CategoryModel(R.drawable.fram,"Cap"));
        lists.add(new CategoryModel(R.drawable.fme,"Mug"));
        lists.add(new CategoryModel(R.drawable.vector,"PhoneCover"));
        lists.add(new CategoryModel(R.drawable.g,"Gift"));

    category_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CategoryActivity.class);
                startActivity(intent);
            }
        });

    //popular products
        popularProducts_see_all = root.findViewById(R.id.popular_see_all);
        rec_popular= root.findViewById(R.id.rec_popular);
        List<ProductsModel> productsModels = new ArrayList<>();

        ProductsAdapter productsAdapter = new ProductsAdapter(getActivity(),productsModels);
        RecyclerView.LayoutManager pManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rec_popular.setLayoutManager(pManager);
        rec_popular.setAdapter(productsAdapter);
        rec_popular.setNestedScrollingEnabled(false);
        rec_popular.setHasFixedSize(true);

        productsModels.add(new ProductsModel(R.drawable.ab,"Cap","৳300","  ৳400","Cap"));
        productsModels.add(new ProductsModel(R.drawable.abd,"T_shirt","৳500","  ৳600","Shirt"));
        productsModels.add(new ProductsModel(R.drawable.ac,"Phone Cover","৳200","  ৳300","Phone Cover"));
        productsModels.add(new ProductsModel(R.drawable.acd,"T_shirt","৳500","  ৳600","Shirt"));
        productsModels.add(new ProductsModel(R.drawable.akh,"Hoodies","৳800","   ৳1000","Hoodies"));
        productsModels.add(new ProductsModel(R.drawable.akl,"Hoodies","৳800","  ৳1000","Hoodies"));

       /* popularProducts_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),PopularProduct.class);
                startActivity(intent);
            }
        });*/

        //latest products

        latestProducts_see_all = root.findViewById(R.id.latest_see_all);
        rec_latest = root.findViewById(R.id.rec_latest);
        List<LatestModel> latestModels = new ArrayList<>();

        LatestProductsAdapter latestProductsAdapter = new LatestProductsAdapter(getActivity(),latestModels);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rec_latest.setLayoutManager(layoutManager);
        rec_latest.setAdapter(latestProductsAdapter);
        rec_latest.setNestedScrollingEnabled(false);
        rec_latest.setHasFixedSize(true);

        latestModels.add(new LatestModel(R.drawable.am,"(500 reviews)","Hoodies","৳1000","  ৳1500","Hoodies"));
        latestModels.add(new LatestModel(R.drawable.alm,"(450 reviews)","Mens T_shirt","৳700","  ৳1000","Shirt"));
        latestModels.add(new LatestModel(R.drawable.akl,"(488 reviews)","Hoodies","৳1000","  ৳1500","Hoodies"));
        latestModels.add(new LatestModel(R.drawable.ad,"(389 reviews)","Phone Cover","৳700","  ৳1500","Phone Cover"));
        latestModels.add(new LatestModel(R.drawable.mug,"(397 reviews)","Mug","৳300","  ৳500","Mug"));
        latestModels.add(new LatestModel(R.drawable.ac,"(356 reviews)","Phone Cover","৳100","  ৳300","Phone Cover"));
        latestModels.add(new LatestModel(R.drawable.abd,"(388 reviews)","Women T_shirt","৳300","  ৳500","Shirt"));

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

        newProductsModels.add(new NewProductsModel(R.drawable.abd,"(289 reviews)","Women T_shirt","৳500","  ৳800","Shirt"));
        newProductsModels.add(new NewProductsModel(R.drawable.mug,"(489 reviews)","Mug","৳300","  ৳500","Mug"));
        newProductsModels.add(new NewProductsModel(R.drawable.ab,"(389 reviews)","Cap","৳300","  ৳500","Cap"));
        newProductsModels.add(new NewProductsModel(R.drawable.ad,"(257 reviews)","Phone Cover","৳200","  ৳500","Phone Cover"));
        newProductsModels.add(new NewProductsModel(R.drawable.am,"(380 reviews)","Hoodies","৳1000","  ৳1500","Hoodies"));
        newProductsModels.add(new NewProductsModel(R.drawable.akl,"(269 reviews)","Hoodies","৳1500","  ৳2000","Hoodies"));

        return root;
    }
}