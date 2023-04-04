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

public class UploadAdapter extends RecyclerView.Adapter<UploadAdapter.ViewHolder> {

    Context context;
    List<UploadModel> uploadModels;


    public UploadAdapter(Context context, List<UploadModel> uploadModels) {
        this.context = context;
        this.uploadModels = uploadModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upload_layout_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(uploadModels.get(position).getImg()).into(holder.pendingImg);
        holder.nameText.setText(uploadModels.get(position).getProduct_name());
        holder.uploadText.setText(uploadModels.get(position).getProduct_type());

    }

    @Override
    public int getItemCount() {
        return uploadModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pendingImg;
        TextView nameText,uploadText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pendingImg = itemView.findViewById(R.id.pending_Img);
            nameText = itemView.findViewById(R.id.nameText);
            uploadText = itemView.findViewById(R.id.uploadText);

        }
    }
}


