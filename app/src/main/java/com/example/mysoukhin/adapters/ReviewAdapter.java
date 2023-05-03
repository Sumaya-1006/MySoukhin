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
import com.example.mysoukhin.models.ReviewModel;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    List<ReviewModel> models;

    public ReviewAdapter(Context context, List<ReviewModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(models.get(position).getProductImg()).into(holder.imageView);
        holder.title.setText("title : "+models.get(position).getProductTitle());
        holder.rating.setText("rating : "+models.get(position).getRating());
        holder.reviews.setText("review : "+models.get(position).getReviews());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, uid, rating,reviews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.reviewImg);
            title = itemView.findViewById(R.id.review_title);
            rating = itemView.findViewById(R.id.reviewRating);
            reviews = itemView.findViewById(R.id.review_text);

        }
    }
}
