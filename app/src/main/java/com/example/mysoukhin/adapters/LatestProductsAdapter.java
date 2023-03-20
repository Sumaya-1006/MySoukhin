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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.ui.CartsActivity;
import com.example.mysoukhin.ui.ProductDetailsActivity;

import java.util.List;

public class LatestProductsAdapter extends RecyclerView.Adapter<LatestProductsAdapter.ViewHolder> {
    Context context;
    List<LatestModel> latestModels;

    public LatestProductsAdapter(Context context, List<LatestModel> latestModels) {
        this.context = context;
        this.latestModels = latestModels;
    }


    @NonNull
    @Override
    public LatestProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LatestProductsAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_item_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LatestProductsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(latestModels.get(position).getProductImg()).into(holder.imageView);
        holder.rating.setText(latestModels.get(position).getRating());
        holder.title.setText(latestModels.get(position).getProductTitle());
        holder.product_price.setText(latestModels.get(position).getProductPrice());
        holder.product_oldPrice.setText(latestModels.get(position).getOldPrice());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("details",latestModels.get(position));
                context.startActivity(intent);
            }
        });

        holder.floating_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(context, CartsActivity.class);
                intent.putExtra("cart",latestModels.get(position));
                context.startActivity(intent);*/


            }
        });
    }

    @Override
    public int getItemCount() {
        return latestModels.size();
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