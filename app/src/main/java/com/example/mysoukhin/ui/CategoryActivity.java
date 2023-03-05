package com.example.mysoukhin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mysoukhin.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        this.setTitle("Category");

        ImageSlider imageSlider = findViewById(R.id.image_slide);
        List<SlideModel> slideModel = new ArrayList<>();
        slideModel.add(new SlideModel(R.drawable.banner1, ScaleTypes.CENTER_CROP));
        slideModel.add(new SlideModel(R.drawable.banner2, ScaleTypes.CENTER_CROP));
        slideModel.add(new SlideModel(R.drawable.banner3, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModel);
    }
}