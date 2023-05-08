package com.example.mysoukhin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.OrderModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    List<OrderModel> orderModels;

    public OrderAdapter(Context context, List<OrderModel> orderModels) {
        this.context = context;
        this.orderModels = orderModels;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(orderModels.get(position).getProductImg()).into(holder.imageView);
        holder.title.setText(orderModels.get(position).getProductTitle());
        holder.product_price.setText("Price : "+orderModels.get(position).getProductPrice() + "৳");
        holder.quantity.setText("Quantity : "+orderModels.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return  orderModels.size();
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