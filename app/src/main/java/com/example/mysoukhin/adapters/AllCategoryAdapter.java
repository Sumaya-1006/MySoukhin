package com.example.mysoukhin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.AllCategoryModel;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.ui.ProductDetailsActivity;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.ViewHolder> {
    Context context;
    List<AllCategoryModel> allCategoryModels;


    public AllCategoryAdapter(Context context, List<AllCategoryModel> allCategoryModels) {
        this.context = context;
        this.allCategoryModels = allCategoryModels;
    }


    @NonNull
    @Override
    public AllCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllCategoryAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_item_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(allCategoryModels.get(position).getProductImg()).into(holder.imageView);
        holder.title.setText(allCategoryModels.get(position).getProductTitle());
        holder.product_price.setText(allCategoryModels.get(position).getProductPrice());
        holder.product_oldPrice.setText(allCategoryModels.get(position).getOldPrice());

        holder.floating_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return allCategoryModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,check_box,floating_img;
        TextView title,rating,product_price,product_oldPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title);
            check_box = itemView.findViewById(R.id.check_box);
            floating_img = itemView.findViewById(R.id.floating_img);
            rating = itemView.findViewById(R.id.ratingId);
            product_price = itemView.findViewById(R.id.product_priceId);
            product_oldPrice = itemView.findViewById(R.id.product_OldPriceId);

        }
    }
}