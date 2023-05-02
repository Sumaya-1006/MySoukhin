package com.example.mysoukhin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysoukhin.R;
import com.example.mysoukhin.models.AddressModel;
import com.example.mysoukhin.ui.CheckOutActivity;
import com.example.mysoukhin.ui.MainActivity;
import com.example.mysoukhin.ui.ShippingAddressActivity;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context context;
    List<AddressModel> model;
    private RadioButton selectedBtn;

    public AddressAdapter(Context context, List<AddressModel> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.ViewHolder holder, int position) {
        holder.address_name.setText(model.get(position).getName());
        holder.address_phn.setText(model.get(position).getPhoneNum());
        holder.address_details.setText(model.get(position).getAddress());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, CheckOutActivity.class);
                holder.radioButton.setSelected(true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity(intent);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedBtn !=null){
                    selectedBtn.setChecked(true);

                }

                Intent intent = new Intent(context, ShippingAddressActivity.class);
                context.startActivity(intent);
                FirebaseDatabase.getInstance().getReference().child("address").removeValue();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address_name,address_details,address_phn;
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address_name = itemView.findViewById(R.id.address_name);
            address_details = itemView.findViewById(R.id.address_details);
            address_phn = itemView.findViewById(R.id.address_phn);
            radioButton = itemView.findViewById(R.id.address_img);


        }
    }
}
