package com.example.mysoukhin.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.models.SeeAllModel;
import com.example.mysoukhin.ui.CheckOutActivity;
import com.example.mysoukhin.ui.ProductDetailsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    List<CartItemModel> cartItemModelList;

    public CartAdapter(Context context, List<CartItemModel> cartItemModelList) {
        this.context = context;
        this.cartItemModelList = cartItemModelList;
    }


    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(cartItemModelList.get(position).getProductImage()).into(holder.product_image);
        holder.product_title.setText(cartItemModelList.get(position).getProducttitle());
        holder.price.setText(cartItemModelList.get(position).getPrice());
      //  holder.quan.setText(cartItemModelList.get(position).getQuantity());
        holder.Cart_ItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.MinusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.MinusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image,PlusIcon,MinusIcon,Cart_ItemDelete;
        TextView product_title,price,quan,quantityText;
        Button checkout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            PlusIcon = itemView.findViewById(R.id.PlusIcon);
            MinusIcon = itemView.findViewById(R.id.MinusIcon);
            Cart_ItemDelete = itemView.findViewById(R.id.Cart_ItemDelete);
            product_title = itemView.findViewById(R.id.product_title);
            price = itemView.findViewById(R.id.price);
            quan = itemView.findViewById(R.id.quan);
            quantityText = itemView.findViewById(R.id.quantityText);
            checkout = itemView.findViewById(R.id.checkoutId);

        }
    }
}

