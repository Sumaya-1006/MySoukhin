package com.example.mysoukhin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.LatestModel;
import com.example.mysoukhin.models.NewProductsModel;
import com.example.mysoukhin.models.ProductsModel;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView details_img;
    TextView double_text,allProduct_price,allProduct_oldPrice,category_name,ratingText,
    desTextView,typeText,colorText,stylishText,cottonText,fabricText,sizeTextView;
    Button buyButton,cartButton,sBtn,mBtn,lBtn,xlBtn,xxlBtn;

    NewProductsModel newProductsModel = null;
    LatestModel latestModels = null;
    ProductsModel productsModels = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

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
        sizeTextView = findViewById(R.id.sizeTextView);

        buyButton = findViewById(R.id.buyButton);
        cartButton = findViewById(R.id.cartBtn);
        sBtn = findViewById(R.id.sBtn);
        mBtn = findViewById(R.id.mBtn);
        lBtn = findViewById(R.id.lBtn);
        xlBtn = findViewById(R.id.xlBtn);
        xxlBtn = findViewById(R.id.xxlBtn);

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


    }
}