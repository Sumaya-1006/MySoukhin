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
                .inflate(R.layout.order_history_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.orderProducts.setText(models.get(position).getOrderProducts());
      //  holder.orderPrice.setText(models.get(position).getOrderPrice());
        holder.orderNums.setText(models.get(position).getOrderNums());
        holder.orderDate.setText(models.get(position).getDate());
        holder.OrderCheck.setText( models.get(position).getOrderCheck());


       if(models.get(position).getOrderCheck().equalsIgnoreCase("false")){
            holder.OrderCheck.setText("Order: Pending");
        }
        else{
            holder.OrderCheck.setText("Order: Received");
        }

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, product_price, date,quantity,orderDate, orderNums, orderPrice, orderProducts, OrderCheck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*title = itemView.findViewById(R.id.order_title);
            product_price = itemView.findViewById(R.id.order_price);
            quantity = itemView.findViewById(R.id.history_quantity);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.order_image);
            Calendar calendar = Calendar.getInstance();
            String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
            date.setText("Date : "+currentDate);*/
            orderDate = itemView.findViewById(R.id.orderDate);
            orderNums = itemView.findViewById(R.id.orderNums);
           // orderPrice = itemView.findViewById(R.id.orderPrice);
            orderProducts = itemView.findViewById(R.id.orderProducts);
            OrderCheck = itemView.findViewById(R.id.OrderCheck);


        }
    }
}
