package com.example.mysoukhin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.AllCategoryModel;
import com.example.mysoukhin.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    ImageView categoryHoddies, categoryShirt, categoryCap, categoryMug,
    categoryGift, categoryCover;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        this.setTitle("Category");

        categoryHoddies = findViewById(R.id.categoryHoodies);
        categoryShirt = findViewById(R.id.categoryShirts);
        categoryCap = findViewById(R.id.categoryCap);
        categoryMug = findViewById(R.id.categoryMug);
        categoryGift = findViewById(R.id.categoryGift);
        categoryCover = findViewById(R.id.categoryCover);

        ImageSlider imageSlider = findViewById(R.id.image_slide);
        List<SlideModel> slideModel = new ArrayList<>();
        slideModel.add(new SlideModel(R.drawable.banner1, ScaleTypes.CENTER_CROP));
        slideModel.add(new SlideModel(R.drawable.banner2, ScaleTypes.CENTER_CROP));
        slideModel.add(new SlideModel(R.drawable.banner3, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModel);

        categoryHoddies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             allCategories() ;

            }
        });
        categoryShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCategories() ;
            }
        });
        categoryCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCategories() ;
            }
        });
        categoryMug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCategories() ;
            }
        });
        categoryCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCategories() ;
            }
        });
        categoryGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CategoryGift.class);
                startActivity(intent);
            }
        });


    }

    private void allCategories() {
    }
}