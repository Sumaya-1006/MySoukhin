package com.example.mysoukhin.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.number.IntegerWidth;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.ui.CheckOutActivity;
import com.example.mysoukhin.ui.MainActivity;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    List<CartItemModel> cartItemModels;

    public CartAdapter(Context context, List<CartItemModel> cartItemModels) {
        this.context = context;
        this.cartItemModels = cartItemModels;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartView = LayoutInflater.from(context).inflate(R.layout.cart_item_layout,parent,false);
        return new ViewHolder(cartView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(cartItemModels.get(position).getProductImage()).into(holder.productImage);
        holder.product_title.setText(cartItemModels.get(position).getProducttitle());
        holder.price.setText("Price: "+cartItemModels.get(position).getPrice()+"৳");
        holder.quan.setText(cartItemModels.get(position).getQuantity());

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
                        /*Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);*/
                        FirebaseDatabase.getInstance().getReference().child("carts").removeValue();
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });
        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CheckOutActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage,Cart_ItemDelete;
        TextView product_title,price,quan,quantityText;
        Button checkout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            Cart_ItemDelete = itemView.findViewById(R.id.Cart_ItemDelete);
            product_title = itemView.findViewById(R.id.product_title);
            price = itemView.findViewById(R.id.price);
            quan = itemView.findViewById(R.id.quantity);
            quantityText = itemView.findViewById(R.id.quantityText);
            checkout = itemView.findViewById(R.id.checkId);
        }
    }
}



