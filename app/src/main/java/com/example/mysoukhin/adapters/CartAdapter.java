package com.example.mysoukhin.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.ui.CartsFragment;
import com.example.mysoukhin.ui.MainActivity;
import com.example.mysoukhin.ui.ProductDetailsActivity;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    List<CartItemModel> cartItemModels;
    int totalAmount ;

    public CartAdapter(Context context, List<CartItemModel> cartItemModels) {
        this.context = context;
        this.cartItemModels = cartItemModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartView = LayoutInflater.from(context).inflate(R.layout.cart_item_layout,parent,false);
        return new ViewHolder(cartView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(cartItemModels.get(position).getProductImg()).into(holder.productImage);
        holder.product_title.setText(cartItemModels.get(position).getProductTitle());
        holder.price.setText("Price: "+cartItemModels.get(position).getProductPrice()+"৳");
       // holder.total_price.setText(cartItemModels.get(position).getTotalPrice()+"");
        holder.quan.setText(cartItemModels.get(position).getQuantity());
      //  holder.radioButton.setText(cartItemModels.get(position).getChecked());

       /* holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    totalAmount = Integer.parseInt(cartItemModels.get(position).getProductPrice())+totalAmount;
                    Log.d("tag", String.valueOf(totalAmount));
                    Intent intent = new Intent("MyTotalAmount");
                    intent.putExtra("total_amount",totalAmount);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    DatabaseReference x = FirebaseDatabase.getInstance().getReference().child("carts");

                    x.child("amount").setValue(totalAmount);


                }catch (Exception e){

                }

            }
        });*/

        //pass total amount
        try{
            totalAmount = Integer.parseInt(cartItemModels.get(position).getProductPrice())+totalAmount;
            Log.d("tag", String.valueOf(totalAmount));
            Intent intent = new Intent("MyTotalAmount");
            intent.putExtra("total_amount",totalAmount);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


        }catch (Exception e){

        }


        holder.Cart_ItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ProductName = "";
                ProductName = holder.product_title.getText().toString();
                FirebaseDatabase.getInstance().getReference().child("cart").child(ProductName).removeValue();
                Toast.makeText(context, "You are successfully delete", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage,Cart_ItemDelete,increment,decrement;
        TextView product_title,price,quan,quantityText,total_price;
       // RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            Cart_ItemDelete = itemView.findViewById(R.id.Cart_ItemDelete);
            product_title = itemView.findViewById(R.id.product_title);
            price = itemView.findViewById(R.id.price);
            quan = itemView.findViewById(R.id.quantity);
           // total_price = itemView.findViewById(R.id.totalprice);
            quantityText = itemView.findViewById(R.id.quantityText);
           // radioButton = itemView.findViewById(R.id.radioBtn);
          /*  increment = itemView.findViewById(R.id.IncrementIcon);
            decrement = itemView.findViewById(R.id.decrementIcon);*/


        }
    }
}



