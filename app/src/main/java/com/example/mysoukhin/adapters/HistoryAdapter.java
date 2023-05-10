package com.example.mysoukhin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysoukhin.R;
import com.example.mysoukhin.models.HistoryModel;
import com.example.mysoukhin.ui.PendingOrder;
import com.example.mysoukhin.ui.ScanQRCodeActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
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
            holder.ScanQrCode.setVisibility(View.VISIBLE);

            /*  DatabaseReference root = FirebaseDatabase.getInstance().getReference();
            DatabaseReference x = root.child("Order");
            x.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    FirebaseDatabase t = FirebaseDatabase.getInstance();
                    String key  = t.getReference("Pending order").push().getKey();
                    root.child("Pending order").child(key).child("pendingProducts").setValue(snapshot.getValue());
                    root.child("Pending order").child(key).child("Date").setValue(String.valueOf(new SimpleDateFormat("dd MMM yyyy hh:mm a").format(Calendar.getInstance().getTime())));
                    root.child("Pending order").child(key).child("IsChecked").setValue("false");

                    Toast.makeText( context ,"Confirmed Completed" , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, PendingOrder.class);
                    context.startActivity(intent);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/

        }
        else{
            holder.OrderCheck.setText("Order: Received");
            holder.ScanQrCode.setVisibility(View.GONE);
        }

        holder.ScanQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ScanQRCodeActivity.class);
                intent.putExtra("OrderId",models.get(position).getOrderID());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button ScanQrCode;
        TextView orderDate, orderNums, orderPrice, orderProducts, OrderCheck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderDate = itemView.findViewById(R.id.orderDate);
            orderNums = itemView.findViewById(R.id.orderNums);
            // orderPrice = itemView.findViewById(R.id.orderPrice);
            orderProducts = itemView.findViewById(R.id.orderProducts);
            OrderCheck = itemView.findViewById(R.id.order_Check);
            ScanQrCode = itemView.findViewById(R.id.ScanQRCode);


        }
    }
}