package com.example.mysoukhin.adapters;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.FavouritesClass;
import com.example.mysoukhin.ui.FavouriteFragment;
import com.example.mysoukhin.ui.MainActivity;
import com.example.mysoukhin.ui.ProductDetailsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    Context context;
    List<FavouritesClass> favouritesList;
    private String ProductName = "";

    public FavouriteAdapter(Context context, List<FavouritesClass> favouritesList) {
        this.context = context;
        this.favouritesList = favouritesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourite_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //  FavouritesClass favourites = favouritesList.get(position);
        Glide.with(context).load(favouritesList.get(position).getProductImg()).into(holder.image_View);
        holder.title.setText(favouritesList.get(position).getProductTitle());
        holder.check_box.setImageResource(R.drawable.love_icon);
        holder.productPrice.setText(favouritesList.get(position).getProductPrice());
        holder.productOldPrice.setText(favouritesList.get(position).getOldPrice());

        holder.check_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_delete_dialog);
                Button yes = dialog.findViewById(R.id.yesId);
                Button no = dialog.findViewById(R.id.no_Id);
                ProductName = holder.title.getText().toString();

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                yes.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        FirebaseDatabase.getInstance().getReference().child("favourites").child(ProductName).removeValue();

                        Toast.makeText(context, "You are successfully delete", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        dialog.dismiss();

                    }
                });
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
      /*  if (favouritesList == null) {
            return 0;
        }*/

        return favouritesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_View,check_box,floatingImg;
        TextView title,rating,productPrice,productOldPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_View = itemView.findViewById(R.id.imageViews);
            title = itemView.findViewById(R.id.titles);
            check_box = itemView.findViewById(R.id.check_Box);
            floatingImg = itemView.findViewById(R.id.floating_Image);
            rating = itemView.findViewById(R.id.ratingsId);
            productPrice = itemView.findViewById(R.id.product_price);
            productOldPrice = itemView.findViewById(R.id.productOldId);


        }
    }
}