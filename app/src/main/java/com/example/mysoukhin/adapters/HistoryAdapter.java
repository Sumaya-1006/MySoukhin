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
import com.example.mysoukhin.models.HistoryModel;
import com.example.mysoukhin.models.OrderModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    Context context;
    List<HistoryModel> models;

    public HistoryAdapter(Context context, List<HistoryModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_history_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(models.get(position).getProductImg()).into(holder.imageView);
        holder.title.setText(models.get(position).getProductTitle());
        holder.product_price.setText("Price : "+models.get(position).getProductPrice() + "৳");
        holder.quantity.setText("Quantity : "+models.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, product_price, date,quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.order_image);
            title = itemView.findViewById(R.id.order_title);
            product_price = itemView.findViewById(R.id.order_price);
            quantity = itemView.findViewById(R.id.history_quantity);
            date = itemView.findViewById(R.id.date);
            Calendar calendar = Calendar.getInstance();
            String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
            date.setText("Date : "+currentDate);

        }
    }
}
