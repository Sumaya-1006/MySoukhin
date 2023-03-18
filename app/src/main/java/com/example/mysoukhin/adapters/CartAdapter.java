package com.example.mysoukhin.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysoukhin.R;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.ui.CheckOutActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {
    private List<CartItemModel> cartItemModelList;
    Context context;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onDeleteClick(int position);

        void UpdateTotalPrice(String str);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public CartAdapter(Context context, List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()) {
            case 0:
                return CartItemModel.cart_item;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartitemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);

        return new cartItemViewHolder(cartitemview, mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int resource = cartItemModelList.get(position).getProductImage();
        String title = cartItemModelList.get(position).getProducttitle();
        int productPrice = cartItemModelList.get(position).getPrice();
        int quantity = cartItemModelList.get(position).getQuantity();
        ((cartItemViewHolder) holder).setItemDetails(resource, title, productPrice, quantity);
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class cartItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView productimage;
        private TextView producttitle;
        private TextView productprice;
        private TextView productQuantity;
        private ImageView PlusIcon;
        private ImageView MinusIcon;
        private ImageView CartItemDelete;
        private Button checkOutBtn;
        public boolean deletedItem = false;
        int totalpriceVal;
        DatabaseReference root;
        String CurrentUser;
        private FirebaseAuth mAuth;

        public cartItemViewHolder(@NonNull View itemView, final CartAdapter.OnItemClickListener listener) {
            super(itemView);
            productimage = itemView.findViewById(R.id.product_image);
            producttitle = itemView.findViewById(R.id.product_title);
            productprice = itemView.findViewById(R.id.price);
            productQuantity = itemView.findViewById(R.id.quan);
            PlusIcon = itemView.findViewById(R.id.PlusIcon);
            MinusIcon = itemView.findViewById(R.id.MinusIcon);
            checkOutBtn = itemView.findViewById(R.id.checkoutId);

            CartItemDelete = itemView.findViewById(R.id.Cart_ItemDelete);
            root = FirebaseDatabase.getInstance().getReference();
            mAuth = FirebaseAuth.getInstance();
            CurrentUser = mAuth.getCurrentUser().getUid();

            checkOutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,CheckOutActivity.class);
                    context.startActivity(intent);


                }
            });


            CartItemDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                            countTotalPrice();

                        }
                    }
                }
            });
        }

        void setItemDetails(int resource, final String title, final int productPriceText, int quantity) {

            Picasso.get().load(resource).into(productimage);
            producttitle.setText(title);
            productQuantity.setText(String.valueOf(quantity));

             PlusIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productQuantity.setText(String.valueOf(Integer.parseInt(productQuantity.getText().toString()) + 1));
                    productprice.setText("Price: " + String.valueOf(productPriceText * Integer.parseInt(productQuantity.getText().toString())));
                    root.child("cart").child(CurrentUser).child(title).child("quantity").setValue(productQuantity.getText().toString());
                    countTotalPrice();

                }
            });

           MinusIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.valueOf(productQuantity.getText().toString()) > 1) {
                        productQuantity.setText(String.valueOf(Integer.parseInt(productQuantity.getText().toString()) - 1));
                        productprice.setText("Price: "+String.valueOf(productPriceText * Integer.parseInt(productQuantity.getText().toString())));
                        root.child("cart").child(CurrentUser).child(title).child("quantity").setValue(productQuantity.getText().toString());
                        countTotalPrice();
                    }
                }
            });

        }


    }


    public void countTotalPrice() {
        final DatabaseReference root = FirebaseDatabase.getInstance().getReference();

        DatabaseReference m = root.child("cart");

        final String CurrentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        ValueEventListener valueEventListener = new ValueEventListener() {
            int totalpriceVal = 0;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.child(CurrentUser).getChildren()) {
                        if (!dataSnapshot.getKey().equals("totalPrice")) {

                            String cartItemPrice = dataSnapshot.child("productPrice").getValue(String.class).toString();
                            String quantity = dataSnapshot.child("quantity").getValue(String.class).toString();
                            totalpriceVal += Integer.parseInt(cartItemPrice) * Integer.parseInt(quantity);
                        }

                    }
                    root.child("cart").child(CurrentUser).child("totalPrice").setValue(String.valueOf(totalpriceVal));
                    mListener.UpdateTotalPrice(String.valueOf(totalpriceVal));
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        m.addListenerForSingleValueEvent(valueEventListener);


    }

}


