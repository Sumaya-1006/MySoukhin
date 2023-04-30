package com.example.mysoukhin.adapters;

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
import com.example.mysoukhin.models.UploadModel;

import java.util.List;

public class DesignAdapter extends RecyclerView.Adapter<DesignAdapter.ViewHolder> {

    Context context;
    List<UploadModel> uploadModels;


    public DesignAdapter(Context context, List<UploadModel> uploadModels) {
        this.context = context;
        this.uploadModels = uploadModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_design, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(uploadModels.get(position).getImg()).into(holder.designImg);
        holder.designText.setText(uploadModels.get(position).getProduct_type());

    }

    @Override
    public int getItemCount() {
        return uploadModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView designImg;
        TextView designText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            designImg = itemView.findViewById(R.id.design_img);
            designText = itemView.findViewById(R.id.design_txt);

        }
    }
}


