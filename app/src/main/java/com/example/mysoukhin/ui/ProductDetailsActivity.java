package com.example.mysoukhin.ui;

import static java.lang.String.valueOf;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.NewProductsModel;
import com.example.mysoukhin.models.ProductsModel;
import com.example.mysoukhin.models.SeeAllModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView details_img,PlusIcon,MinusIcon;
    TextView double_text,allProduct_price,allProduct_oldPrice,category_name,ratingText,
            desTextView,typeText,colorText,stylishText,cottonText,fabricText,quantity;
    Button buyButton,cartButton;
    NewProductsModel newProductsModel = null;
    LatestModel latestModels = null;
    ProductsModel productsModels = null;
    SeeAllModel seeAllModel = null;
    FirebaseDatabase database;
    int totalQuantity = 1;
    int totalPrice = 1;
    String TAG = "first log";
    private String ProductImage, Category, OldPrice, ProductRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        database = FirebaseDatabase.getInstance();

        // new products
        final Object obj = getIntent().getSerializableExtra("details");
        if(obj instanceof NewProductsModel)
            newProductsModel = (NewProductsModel) obj;

        //latest products

        final Object obj1 = getIntent().getSerializableExtra("details");
        if(obj1 instanceof LatestModel)
            latestModels = (LatestModel) obj1;

        //popular products

        final Object obj2 = getIntent().getSerializableExtra("details");
        if(obj2 instanceof ProductsModel)
            productsModels = (ProductsModel) obj2;


        //all products

        final Object obj3 = getIntent().getSerializableExtra("details");

        if(obj3 instanceof SeeAllModel)
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

        //new Products
        if(newProductsModel !=null){
            Glide.with(getApplicationContext()).load(newProductsModel.getProductImg()).into(details_img);
            double_text.setText(newProductsModel.getProductTitle());
            allProduct_price.setText(newProductsModel.getOldPrice());
            allProduct_oldPrice.setText(newProductsModel.getOldPrice());
            category_name.setText(newProductsModel.getCategory());
            ratingText.setText(newProductsModel.getRating());

            //have sending data
            ProductImage= getIntent().getStringExtra("productImg\n");
            Category = getIntent().getStringExtra("category\n");
            ProductRating= getIntent().getStringExtra("rating\n");
            OldPrice = getIntent().getStringExtra("oldPrice\n");
            try{
                totalPrice = Integer.valueOf(newProductsModel.getProductPrice()) * totalQuantity;

            }catch (Exception e){

            }


        }

        //latest Products

        if(latestModels !=null){
            Glide.with(getApplicationContext()).load(latestModels.getProductImg()).into(details_img);
            double_text.setText(latestModels.getProductTitle());
            allProduct_price.setText(latestModels.getProductPrice());
            allProduct_oldPrice.setText(latestModels.getOldPrice());
            category_name.setText(latestModels.getCategory());
            ratingText.setText(latestModels.getRating());
            //have sending data
            ProductImage= getIntent().getStringExtra("productImg\n");
            Category = getIntent().getStringExtra("category\n");
            ProductRating= getIntent().getStringExtra("rating\n");
            OldPrice = getIntent().getStringExtra("oldPrice\n");
           try{
               totalPrice = Integer.valueOf(latestModels.getProductPrice()) * totalQuantity;

           }catch (Exception e){

           }


        }

        //popular Products

        if(productsModels !=null) {
            Glide.with(getApplicationContext()).load(productsModels.getProductImg()).into(details_img);
            double_text.setText(productsModels.getProductTitle());
            allProduct_price.setText(productsModels.getProductPrice());
            allProduct_oldPrice.setText(productsModels.getOldPrice());
            category_name.setText(productsModels.getCategory());

            //have sending data
            ProductImage= getIntent().getStringExtra("productImg\n");
            Category = getIntent().getStringExtra("category\n");
            ProductRating= getIntent().getStringExtra("rating\n");
            OldPrice = getIntent().getStringExtra("oldPrice\n");
            try{
                totalPrice = Integer.valueOf(productsModels.getProductPrice()) * totalQuantity;

            }catch (Exception e){

            }


        }
        //show all products

        if(seeAllModel!=null) {
            Glide.with(getApplicationContext()).load(seeAllModel.getProductImg()).into(details_img);
            double_text.setText(seeAllModel.getProductTitle());
            allProduct_price.setText(seeAllModel.getProductPrice());
            allProduct_oldPrice.setText(seeAllModel.getOldPrice());
            category_name.setText(seeAllModel.getCategory());
            try{
                totalPrice = Integer.valueOf(seeAllModel.getProductPrice()) * totalQuantity;

            }catch (Exception e){

            }


        }

        //show all category

        if(seeAllModel!=null) {
            Glide.with(getApplicationContext()).load(seeAllModel.getProductImg()).into(details_img);
            double_text.setText(seeAllModel.getProductTitle());
            allProduct_price.setText(seeAllModel.getProductPrice());
            allProduct_oldPrice.setText(seeAllModel.getOldPrice());
            category_name.setText(seeAllModel.getCategory());
        }
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CheckOutActivity.class);
                startActivity(intent);
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containerId,new CartsFragment()).commit();
                setProductData();


            }
        });
        PlusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(totalQuantity < 10){
                     totalQuantity++;
                     quantity.setText(valueOf(totalQuantity));
                     if(newProductsModel !=null){
                         totalPrice = Integer.valueOf(newProductsModel.getProductPrice()) * totalQuantity;
                         allProduct_price.setText(valueOf(totalPrice));
                     }
                     if(latestModels !=null){
                         totalPrice = Integer.valueOf(latestModels.getProductPrice()) * totalQuantity;
                         allProduct_price.setText(valueOf(totalPrice));
                     }
                     if(productsModels !=null){
                         totalPrice = Integer.valueOf(productsModels.getProductPrice()) * totalQuantity;
                         allProduct_price.setText(valueOf(totalPrice));
                     }
                     if(seeAllModel!=null){
                         totalPrice = Integer.valueOf(seeAllModel.getProductPrice()) * totalQuantity;
                     }


                 }

            }
        });
        MinusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(valueOf(totalQuantity));

                    if(newProductsModel !=null){
                        totalPrice = Integer.valueOf(newProductsModel.getProductPrice()) * totalQuantity;
                        allProduct_price.setText(valueOf(totalPrice));
                    }
                    if(latestModels !=null){
                        totalPrice = Integer.valueOf(latestModels.getProductPrice()) * totalQuantity;
                        allProduct_price.setText(valueOf(totalPrice));
                    }
                    if(productsModels !=null){
                        totalPrice = Integer.valueOf(productsModels.getProductPrice()) * totalQuantity;
                        allProduct_price.setText(valueOf(totalPrice));
                    }
                    if(seeAllModel!=null){
                        totalPrice = Integer.valueOf(seeAllModel.getProductPrice()) * totalQuantity;
                        allProduct_price.setText(valueOf(totalPrice));
                    }

                }
            }

        });

    }

    private void setProductData() {
        CartItemModel model = new CartItemModel();
        model.setProducttitle(double_text.getText().toString());
        model.setPrice(allProduct_price.getText().toString());
        model.setQuantity((quantity.getText().toString()));
        model.setProductImage(details_img.toString());
       // Glide.with(getApplicationContext()).load(model.getProductImage()).into(details_img);

        Log.d(TAG,"image found");
        // Picasso.get().load(ProductImage).into(details_img);*/

        database.getReference().child("cart").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ProductDetailsActivity.this, "Add to cart successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProductDetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


