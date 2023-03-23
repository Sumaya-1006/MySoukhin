package com.example.mysoukhin.adapters;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.ui.CheckOutActivity;
import com.example.mysoukhin.ui.LoginActivity;
import com.example.mysoukhin.ui.MainActivity;
import com.example.mysoukhin.ui.ProductDetailsActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

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
        View cartItemView = (LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout, parent, false));
        return new ViewHolder(cartItemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(cartItemModelList.get(position).getProductImage()).into(holder.product_image);
        holder.product_title.setText(cartItemModelList.get(position).getProducttitle());
        holder.price.setText(cartItemModelList.get(position).getPrice());
       // holder.quan.setText(cartItemModelList.get(position).getQuantity());
        holder.Cart_ItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_delete_dialog);
                Button yes = dialog.findViewById(R.id.yesId);
                Button no = dialog.findViewById(R.id.no_Id);

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(context, "You are successfully delete", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        FirebaseDatabase.getInstance().getReference().child("carts").removeValue();
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });
        holder.PlusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* int quantity = cartItemModelList.get(position).getQuantity();
                quantity++;
                cartItemModelList.get(position).setQuantity(quantity);
                notifyDataSetChanged();
                updatePrice();*/


            }
        });
        holder.MinusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               minusCartItem(holder,cartItemModelList.get(position));
            }
        });
        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,CheckOutActivity.class);
                context.startActivity(intent);
                Toast.makeText(context, "Your order is confirmed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteFromFirebase(CartItemModel cartModel) {

    }

    private void minusCartItem(ViewHolder holder, CartItemModel cartItemModel) {
        if(cartItemModel.getQuantity() >1){
           cartItemModel.setQuantity(cartItemModel.getQuantity()-1);
           cartItemModel.setPrice(String.valueOf(cartItemModel.getQuantity()*Float.parseFloat(cartItemModel.getPrice())));
           holder.quan.setText(new StringBuffer().append(cartItemModel.getQuantity()));


        }
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
            quan = itemView.findViewById(R.id.quantity);
            quantityText = itemView.findViewById(R.id.quantityText);
            checkout = itemView.findViewById(R.id.checkoutId);

        }
    }
    public void updatePrice(){
        int sum =0,i;
        for(i=0; i<cartItemModelList.size();i++){

        }




    }

}

