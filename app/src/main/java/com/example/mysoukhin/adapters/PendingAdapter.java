package com.example.mysoukhin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.mysoukhin.models.PendingModel;

import java.util.List;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder> {
    Context context;
    List<PendingModel> models;

    public PendingAdapter(Context context, List<PendingModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public PendingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PendingAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PendingAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(models.get(position).getProductImg()).into(holder.imageView);
        holder.title.setText(models.get(position).getProductTitle());
        holder.product_price.setText("Price : "+models.get(position).getProductPrice() + "৳");
        holder.quantity.setText("Quantity : "+models.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return  models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title,product_price,quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.order_image);
            title = itemView.findViewById(R.id.order_title);
            product_price = itemView.findViewById(R.id.order_price);
            quantity = itemView.findViewById(R.id.order_quant);

        }
    }
}