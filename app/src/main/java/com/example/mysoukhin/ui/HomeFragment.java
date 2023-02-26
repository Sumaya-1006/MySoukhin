package com.example.mysoukhin.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CategoryAdapter;
import com.example.mysoukhin.models.CategoryModel;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView cat_recycler;
    CategoryAdapter categoryAdapter;
    TextView category_see_all;


    @SuppressLint("NotifyDataSetChanged")
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
        cat_recycler = root.findViewById(R.id.rec_category);
        cat_recycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,true));
        cat_recycler.setHasFixedSize(true);
        cat_recycler.setAdapter(categoryAdapter);
        List<CategoryModel> lists = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(),lists);

        lists.add(new CategoryModel(R.drawable.ggggg,"T-Shirt"));
        lists.add(new CategoryModel(R.drawable.gggggg,"Hoodies"));
        lists.add(new CategoryModel(R.drawable.gggg,"Cap"));
        lists.add(new CategoryModel(R.drawable.ggg,"Mug"));
        lists.add(new CategoryModel(R.drawable.gg,"PhoneCover"));
        lists.add(new CategoryModel(R.drawable.g,"Gift"));

      /*category_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CategoryActivity.class);
                startActivity(intent);
            }
        });*/

          return root;
    }
}