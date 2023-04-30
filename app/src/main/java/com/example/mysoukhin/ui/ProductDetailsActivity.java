package com.example.mysoukhin.ui;


import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.adapters.CartAdapter;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.NewProductsModel;
import com.example.mysoukhin.models.ProductsModel;
import com.example.mysoukhin.models.SeeAllModel;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView details_img, PlusIcon, MinusIcon;
    TextView double_text, allProduct_price, allProduct_oldPrice, category_name, ratingText,
            desTextView, typeText, colorText, stylishText, cottonText, fabricText, quantity;
    Button buyButton, cartButton;
    NewProductsModel newProductsModel = null;
    LatestModel latestModels = null;
    ProductsModel productsModels = null;
    SeeAllModel seeAllModel = null;
    FirebaseDatabase database;
    int totalQuantity = 1;
    int totalAmount;
    int totalPrice = 1;
    private String ProductImage, ProductQuantity, OldPrice, ProductRating, ProductName, ProductPrice;
    static NotificationBadge badge;
    static ImageView icon;

    static  int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        database = FirebaseDatabase.getInstance();

        // new products
        final Object obj = getIntent().getSerializableExtra("details");
        if (obj instanceof NewProductsModel)
            newProductsModel = (NewProductsModel) obj;

        //latest products

        final Object obj1 = getIntent().getSerializableExtra("details");
        if (obj1 instanceof LatestModel)
            latestModels = (LatestModel) obj1;

        //popular products

        final Object obj2 = getIntent().getSerializableExtra("details");
        if (obj2 instanceof ProductsModel)
            productsModels = (ProductsModel) obj2;


        //all products

        final Object obj3 = getIntent().getSerializableExtra("details");

        if (obj3 instanceof SeeAllModel)
            seeAllModel = (SeeAllModel) obj3;

        details_img = findViewById(R.id.details_img);
        double_text = findViewById(R.id.doubleText);
        allProduct_price = findViewById(R.id.allProduct_priceId);
        allProduct_oldPrice = findViewById(R.id.allProduct_OldPriceId);
        category_name = findViewById(R.id.categoryId);

        ratingText = findViewById(R.id.ratingTextId);
        desTextView = findViewById(R.id.desTextView);
        typeText = findViewById(R.id.typeText);
        colorText = findViewById(R.id.colorText);

        stylishText = findViewById(R.id.stylishText);
        cottonText = findViewById(R.id.cottonText);
        fabricText = findViewById(R.id.fabricText);

        buyButton = findViewById(R.id.buyButton);
        cartButton = findViewById(R.id.cartBtn);

        PlusIcon = findViewById(R.id.PlusIcons);
        MinusIcon = findViewById(R.id.MinusIcons);
        quantity = findViewById(R.id.quans);
        icon = findViewById(R.id.icon);
        badge = findViewById(R.id.badge_id);

        ratingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);*/

            }
        });


        //new Products
        if (newProductsModel != null) {
            Glide.with(getApplicationContext()).load(newProductsModel.getProductImg()).into(details_img);
            double_text.setText(newProductsModel.getProductTitle());
            allProduct_price.setText(newProductsModel.getProductPrice());
            allProduct_oldPrice.setText(newProductsModel.getOldPrice());
            category_name.setText(newProductsModel.getCategory());
            totalAmount = Integer.valueOf(newProductsModel.getProductPrice()) * totalQuantity;
            totalPrice = Integer.valueOf(newProductsModel.getProductPrice()) * totalQuantity;

        }

        //latest Products

        if (latestModels != null) {
            Glide.with(getApplicationContext()).load(latestModels.getProductImg()).into(details_img);
            double_text.setText(latestModels.getProductTitle());
            allProduct_price.setText(latestModels.getProductPrice());
            allProduct_oldPrice.setText(latestModels.getOldPrice());
            category_name.setText(latestModels.getCategory());
            ratingText.setText(latestModels.getRating());


            //have sending data
            ProductImage = getIntent().getStringExtra("productImg\n");
            ProductRating = getIntent().getStringExtra("rating\n");
            OldPrice = getIntent().getStringExtra("oldPrice\n");
            totalAmount = Integer.valueOf(latestModels.getProductPrice()) * totalQuantity;
            totalPrice = Integer.valueOf(latestModels.getProductPrice()) * totalQuantity;

        }

        //popular Products

        if (productsModels != null) {
            Glide.with(getApplicationContext()).load(productsModels.getProductImg()).into(details_img);
            double_text.setText(productsModels.getProductTitle());
            allProduct_price.setText(productsModels.getProductPrice());
            allProduct_oldPrice.setText(productsModels.getOldPrice());
            category_name.setText(productsModels.getCategory());
            totalAmount = Integer.valueOf(productsModels.getProductPrice()) * totalQuantity;
            totalPrice = Integer.valueOf(productsModels.getProductPrice()) * totalQuantity;


        }
        //show all products

        if (seeAllModel != null) {
            Glide.with(getApplicationContext()).load(seeAllModel.getProductImg()).into(details_img);
            double_text.setText(seeAllModel.getProductTitle());
            allProduct_price.setText(seeAllModel.getProductPrice());
            allProduct_oldPrice.setText(seeAllModel.getOldPrice());
            category_name.setText(seeAllModel.getCategory());
            totalPrice = Integer.valueOf(seeAllModel.getProductPrice()) * totalQuantity;


        }

        //show all category

        if (seeAllModel != null) {
            Glide.with(getApplicationContext()).load(seeAllModel.getProductImg()).into(details_img);
            double_text.setText(seeAllModel.getProductTitle());
            allProduct_price.setText(seeAllModel.getProductPrice());
            allProduct_oldPrice.setText(seeAllModel.getOldPrice());
            category_name.setText(seeAllModel.getCategory());
        }
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(latestModels != null){
                    totalAmount = Integer.valueOf(latestModels.getProductPrice()) * totalQuantity;
                }
                if(productsModels != null){
                    totalAmount = Integer.valueOf(productsModels.getProductPrice()) * totalQuantity;
                }
                if(newProductsModel != null){
                    totalAmount = Integer.valueOf(newProductsModel.getProductPrice()) * totalQuantity;
                }
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("amount",totalAmount);
                startActivity(intent);

            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.containerId, new CartsFragment()).commit();

                setProductData();

            }
        });
        PlusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(valueOf(totalQuantity));

                    if (newProductsModel != null) {
                        totalPrice = Integer.valueOf(newProductsModel.getProductPrice()) * totalQuantity;
                     //   totalAmount = Integer.valueOf(newProductsModel.getProductPrice()) * totalQuantity;
                        allProduct_price.setText(valueOf(totalPrice));
                    }
                    if (latestModels != null) {
                        totalPrice = Integer.valueOf(latestModels.getProductPrice()) * totalQuantity;
                       //totalAmount = Integer.valueOf(latestModels.getProductPrice()) * totalQuantity;
                        allProduct_price.setText(valueOf(totalPrice));
                    }
                    if (productsModels != null) {
                        totalPrice = Integer.valueOf(productsModels.getProductPrice()) * totalQuantity;
                       // totalAmount = Integer.valueOf(productsModels.getProductPrice()) * totalQuantity;
                       allProduct_price.setText(valueOf(totalPrice));
                    }
                    if (seeAllModel != null) {

                    }

                }
            }
        });
        MinusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(valueOf(totalQuantity));

                    if (newProductsModel != null) {
                        totalPrice = Integer.valueOf(newProductsModel.getProductPrice()) * totalQuantity;
                       // totalAmount = Integer.valueOf(newProductsModel.getProductPrice()) * totalQuantity;
                      allProduct_price.setText(valueOf(totalPrice));
                    }
                    if (latestModels != null) {
                        totalPrice = Integer.valueOf(latestModels.getProductPrice()) * totalQuantity;
                       // totalAmount = Integer.valueOf(latestModels.getProductPrice()) * totalQuantity;
                      allProduct_price.setText(valueOf(totalPrice));
                    }
                    if (productsModels != null) {
                        totalPrice = Integer.valueOf(productsModels.getProductPrice()) * totalQuantity;
                      //  totalAmount = Integer.valueOf(productsModels.getProductPrice()) * totalQuantity;
                       allProduct_price.setText(valueOf(totalPrice));
                    }
                    if (seeAllModel != null) {
                        totalPrice = Integer.valueOf(seeAllModel.getProductPrice()) * totalQuantity;

                    }

                }
            }

        });
    }

    private void setProductData() {
        ProductName = double_text.getText().toString();
        ProductPrice = allProduct_price.getText().toString();
        OldPrice = allProduct_oldPrice.getText().toString();
        ProductQuantity = quantity.getText().toString();

       /* if(productsModels !=null){
           count = Integer.valueOf(totalQuantity);

        }
        if(latestModels !=null){
            count = Integer.valueOf(totalQuantity);

        }
        if(newProductsModel !=null){
            count = Integer.valueOf(totalQuantity);

        }*/
        if(productsModels !=null){
            ProductImage = (productsModels.getProductImg().toString());

        }

        if(latestModels !=null){
            ProductImage = (latestModels.getProductImg().toString());
        }

        if(newProductsModel !=null){
            ProductImage = (newProductsModel.getProductImg().toString());
        }

        DatabaseReference x = FirebaseDatabase.getInstance().getReference().child("cart").child(ProductName);
        x.child("quantity").setValue(ProductQuantity);
        x.child("productImg").setValue(ProductImage);
        x.child("productPrice").setValue(ProductPrice);
        x.child("productTitle").setValue(ProductName);
      //  x.child("count").setValue(count);
      //  x.child("oldPrice").setValue(OldPrice);
        Toast.makeText(this, "Add to cart successfully", Toast.LENGTH_SHORT).show();

    }


}


