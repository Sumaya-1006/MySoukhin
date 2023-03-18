package com.example.mysoukhin.adapters;
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
import com.example.mysoukhin.models.FavouritesClass;
import com.example.mysoukhin.ui.FavouriteFragment;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    Context context;
    List<FavouritesClass> favouritesList;

    public FavouriteAdapter(Context context, List<FavouritesClass> favouritesList) {
        this.context = context;
        this.favouritesList = favouritesList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_item_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavouritesClass favourites = favouritesList.get(position);
        Glide.with(context).load(favouritesList.get(position).getProductImage()).into(holder.image_View);
        holder.title.setText(favourites.getProductTitle());
        holder.check_box.setImageResource(R.drawable.love_icon);
        holder.productPrice.setText(favourites.getProductPrice());
        holder.productOldPrice.setText(favourites.getProductOldPrice());


    }

    @Override
    public int getItemCount() {
        if (favouritesList == null) {
            return 0;
        }

        return favouritesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_View,check_box,floatingImg;
        TextView title,rating,productPrice,productOldPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_View = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title);
            check_box = itemView.findViewById(R.id.check_box);
            floatingImg = itemView.findViewById(R.id.floating_img);
            rating = itemView.findViewById(R.id.ratingId);
            productPrice = itemView.findViewById(R.id.product_priceId);
            productOldPrice = itemView.findViewById(R.id.product_OldPriceId);


        }
    }
}