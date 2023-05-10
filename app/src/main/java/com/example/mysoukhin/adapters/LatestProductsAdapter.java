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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.AddressModel;
import com.example.mysoukhin.models.FavouritesClass;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.UploadModel;
import com.example.mysoukhin.ui.CartsFragment;
import com.example.mysoukhin.ui.FavouriteFragment;
import com.example.mysoukhin.ui.ProductDetailsActivity;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class LatestProductsAdapter extends RecyclerView.Adapter<LatestProductsAdapter.ViewHolder> {
    Context context;
    List<LatestModel> latestModels;

    public LatestProductsAdapter(Context context, List<LatestModel> latestModels) {
        this.context = context;
        this.latestModels = latestModels;
    }


    @NonNull
    @Override
    public LatestProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LatestProductsAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_item_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(latestModels.get(position).getProductImg()).into(holder.imageView);
        holder.rating.setText(latestModels.get(position).getRating());
        holder.title.setText(latestModels.get(position).getProductTitle());
        holder.product_price.setText(latestModels.get(position).getProductPrice()+"৳");
        holder.product_oldPrice.setText(latestModels.get(position).getOldPrice());


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("details",latestModels.get(position));
              //  intent.putExtra("productImg",latestModels.get(position).getProductImg());
                context.startActivity(intent);
            }
        });

        holder.floating_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
       holder.check_box.setOnClickListener(new View.OnClickListener() {
            private  String  ProductName = "", ProductPrice="",OldPrice="", ProductIsFavorite, UserId = " ";
            private String ProductImage= "";
           FirebaseAuth auth;
           String CurrentUser;

            @Override
            public void onClick(View view) {
                if (ProductIsFavorite != null && ProductIsFavorite.equalsIgnoreCase("true")) {
                    holder.check_box.setImageResource(R.drawable.black);
                    ProductIsFavorite = "false";
                    DatabaseReference x = FirebaseDatabase.getInstance().getReference().child("favourites").child(ProductName);
                    x.child(ProductName).removeValue();

                } else {
                    holder.check_box.setImageResource(R.drawable.love_icon);
                    holder.check_box.setVisibility(View.VISIBLE);
                    ProductName = holder.title.getText().toString();
                    ProductPrice = holder.product_price.getText().toString();
                    ProductIsFavorite = "true";
                    ProductImage = (latestModels.get(position).getProductImg().toString());
                    OldPrice = holder.product_oldPrice.getText().toString();

                }
                auth = FirebaseAuth.getInstance();
                CurrentUser = auth.getCurrentUser().getUid();
                DatabaseReference x = FirebaseDatabase.getInstance().getReference().child("favourites").child(CurrentUser).child(ProductName);
                x.child("isFavorite").setValue(true);
                x.child("productImg").setValue(ProductImage);
                x.child("productPrice").setValue(ProductPrice);
                x.child("productTitle").setValue(ProductName);
                x.child("oldPrice").setValue(OldPrice);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FavouriteFragment fragment = new FavouriteFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).addToBackStack(null).commit();
            }
        });
        holder.floating_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                CartsFragment cartsFragment = new CartsFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,cartsFragment).addToBackStack(null).commit();

            }
        });
    }


    @Override
    public int getItemCount() {
        return latestModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,check_box,floating_img;
        TextView title,rating,product_price,product_oldPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title);
            check_box = itemView.findViewById(R.id.check_box);
            floating_img = itemView.findViewById(R.id.floating_img);
            rating = itemView.findViewById(R.id.ratingId);
            product_price = itemView.findViewById(R.id.product_priceId);
            product_oldPrice = itemView.findViewById(R.id.product_OldPriceId);

        }
    }
}