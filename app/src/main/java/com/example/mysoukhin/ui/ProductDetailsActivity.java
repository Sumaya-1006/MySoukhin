package com.example.mysoukhin.ui;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.AllCategoryModel;
import com.example.mysoukhin.models.CartItemModel;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.NewProductsModel;
import com.example.mysoukhin.models.ProductsModel;
import com.example.mysoukhin.models.SeeAllModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView details_img;
    TextView double_text,allProduct_price,allProduct_oldPrice,category_name,ratingText,
    desTextView,typeText,colorText,stylishText,cottonText,fabricText;
    Button buyButton,cartButton;
    Toolbar toolbar;
    NewProductsModel newProductsModel = null;
    LatestModel latestModels = null;
    ProductsModel productsModels = null;
    SeeAllModel seeAllModel = null;
    FirebaseDatabase database;
    private String ProductName, ProductPrice, ProductImage, ProductIsFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        this.setTitle("Product details");

        toolbar = findViewById(R.id.details_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        database = FirebaseDatabase.getInstance();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //have sending data
        ProductName= getIntent().getStringExtra("Product Name");
        ProductPrice= getIntent().getStringExtra("Product Price");
        ProductImage= getIntent().getStringExtra("Product Image");
        ProductIsFavorite= getIntent().getStringExtra("Product IsFavorite");

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

        //new Products
        if(newProductsModel !=null){
            Glide.with(getApplicationContext()).load(newProductsModel.getProductImg()).into(details_img);
            double_text.setText(newProductsModel.getProductTitle());
            allProduct_price.setText(newProductsModel.getProductPrice());
            allProduct_oldPrice.setText(newProductsModel.getOldPrice());
            category_name.setText(newProductsModel.getCategory());
            ratingText.setText(newProductsModel.getRating());
        }

        //latest Products

        if(latestModels !=null){
            Glide.with(getApplicationContext()).load(latestModels.getProductImg()).into(details_img);
            double_text.setText(latestModels.getProductTitle());
            allProduct_price.setText(latestModels.getProductPrice());
            allProduct_oldPrice.setText(latestModels.getOldPrice());
            category_name.setText(latestModels.getCategory());
            ratingText.setText(latestModels.getRating());
        }

        //popular Products

        if(productsModels !=null) {
            Glide.with(getApplicationContext()).load(productsModels.getProductImg()).into(details_img);
            double_text.setText(productsModels.getProductTitle());
            allProduct_price.setText(productsModels.getProductPrice());
            allProduct_oldPrice.setText(productsModels.getOldPrice());
            category_name.setText(productsModels.getCategory());
        }
        //show all products

        if(seeAllModel!=null) {
            Glide.with(getApplicationContext()).load(seeAllModel.getProductImg()).into(details_img);
            double_text.setText(seeAllModel.getProductTitle());
            allProduct_price.setText(seeAllModel.getProductPrice());
            allProduct_oldPrice.setText(seeAllModel.getOldPrice());
            category_name.setText(seeAllModel.getCategory());
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
    }

    private void setProductData() {

        CartItemModel model = new CartItemModel();
        model.setProducttitle(double_text.getText().toString());
        model.setPrice(allProduct_price.getText().toString());
        Glide.with(this).load(model.getProductImage()).into(details_img);
        database = FirebaseDatabase.getInstance();

        database.getReference().child("carts").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id ==R.id.searchBar){

        }
       else if (id == R.id.cartBar) {
            Intent intent = new Intent(getApplicationContext(), CartsFragment.class);
            startActivity(intent);
        }


        return true;
    }
}