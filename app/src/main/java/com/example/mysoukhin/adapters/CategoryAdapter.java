package com.example.mysoukhin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.AllCategoryModel;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.models.CategoryModel;
import com.example.mysoukhin.models.FavouritesClass;
import com.example.mysoukhin.models.SeeAllModel;
import com.example.mysoukhin.ui.ProductDetailsActivity;
import com.example.mysoukhin.ui.ProductsShowAll;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
   Context context;
   List<CategoryModel> lists;

   public CategoryAdapter(Context context, List<CategoryModel> lists) {
        this.context = context;
        this.lists = lists;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(lists.get(position).getImg_url()).into(holder.imageView);
        holder.textView.setText(lists.get(position).getCategory());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
          String category = holder.textView.getText().toString();
          CartAdapter adapter;
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(context, ProductsShowAll.class);
                intent.putExtra("category", lists.get(position).getCategory());
                context.startActivity(intent);*/
/*
                DatabaseReference ref;
                if(category !=null && category.equals("Hoodies")) {
                    ref = FirebaseDatabase.getInstance().getReference("products").child("all hoodies");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                CategoryModel itemModel = dataSnapshot.getValue(CategoryModel.class);
                                lists.add(itemModel);
                            }
                         //   adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }*/

            }
        });
        }


    @Override
    public int getItemCount() {
        return lists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.categoryIcon);
            textView = itemView.findViewById(R.id.categoryName);
        }
    }
}


