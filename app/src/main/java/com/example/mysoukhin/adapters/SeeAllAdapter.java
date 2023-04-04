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
import com.example.mysoukhin.models.SeeAllModel;
import com.example.mysoukhin.ui.ProductDetailsActivity;

import java.util.List;

public class SeeAllAdapter extends RecyclerView.Adapter<SeeAllAdapter.ViewHolder> {
    Context context;
    List<SeeAllModel> seeAllModels;

    public SeeAllAdapter(Context context, List<SeeAllModel> seeAllModels) {
        this.context = context;
        this.seeAllModels = seeAllModels;
    }


    @NonNull
    @Override
    public SeeAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SeeAllAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_all_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull SeeAllAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(seeAllModels.get(position).getProductImg()).into(holder.imageView);
        holder.title.setText(seeAllModels.get(position).getProductTitle());
        holder.product_price.setText("Price: "+seeAllModels.get(position).getProductPrice()+"৳");
        holder.product_oldPrice.setText(seeAllModels.get(position).getOldPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("details",seeAllModels.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seeAllModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,check_box,floating_img;
        TextView title,rating,product_price,product_oldPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.see_all_imageview);
            title = itemView.findViewById(R.id.see_all_title);
            check_box = itemView.findViewById(R.id.see_all_check_box);
            floating_img = itemView.findViewById(R.id.see_all_floating_img);
            rating = itemView.findViewById(R.id.see_all_ratingId);
            product_price = itemView.findViewById(R.id.see_all_product_priceId);
            product_oldPrice = itemView.findViewById(R.id.see_all_product_OldPriceId);

        }
    }
}