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
import com.example.mysoukhin.models.CategoryModel;
import com.example.mysoukhin.models.ProductsModel;
import com.example.mysoukhin.ui.ProductDetailsActivity;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    Context context;
    List<ProductsModel> productsModels;

    public ProductsAdapter(Context context, List<ProductsModel> productsModels) {
        this.context = context;
        this.productsModels = productsModels;
    }


    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_item_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(productsModels.get(position).getProductImg()).into(holder.imageView);
        holder.title.setText(productsModels.get(position).getProductTitle());
        holder.product_price.setText(productsModels.get(position).getProductPrice());
        holder.product_oldPrice.setText(productsModels.get(position).getOldPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("details",productsModels.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsModels.size();
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