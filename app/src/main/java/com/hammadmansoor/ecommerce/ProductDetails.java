package com.hammadmansoor.ecommerce;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import utils.ProductModel;

public class ProductDetails extends AppCompatActivity {
    TextView productTitle,productDesc,productPrice;
    ImageView productImage,navigateShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productDesc = findViewById(R.id.productDesc);
        productTitle = findViewById(R.id.productTitle);
        productPrice = findViewById(R.id.productPrice);
        productImage = findViewById(R.id.productImage);
        navigateShop = findViewById(R.id.navigateShop);

        Intent intent = getIntent();
        if(intent != null){
            ProductModel productModel = (ProductModel) intent.getSerializableExtra("product");

            if(productModel != null){
                Log.d(TAG,"Product received in Product Details");
                Log.d(TAG,productModel.toString());
                populateData(productModel);
            }
        }

        navigateShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetails.this,Shop.class));
            }
        });
    }


    public void populateData(ProductModel productModel){

        productTitle.setText(productModel.getName());
        productDesc.setText(productModel.getDescription());
        productPrice.setText(productModel.getPrice());
        Glide.with(this)
                .load(productModel.getImageUrl())
                .placeholder(R.drawable.newpic)  // Placeholder image while loading
                .into(productImage);
    }
}