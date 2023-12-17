package com.hammadmansoor.ecommerce;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import utils.CartItemModel;
import utils.ProductModel;

public class ProductDetails extends AppCompatActivity {
    TextView productTitle,productDesc,productPrice;
    ImageView productImage,navigateShop;
    Spinner sizeSpinner,colorSpinner;
    EditText quantity;
    Button addToCart;
    FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productDesc = findViewById(R.id.productDesc);
        productTitle = findViewById(R.id.productTitle);
        productPrice = findViewById(R.id.productPrice);
        productImage = findViewById(R.id.productImage);
        navigateShop = findViewById(R.id.navigateShop);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        colorSpinner = findViewById(R.id.colorSpinner);
        addToCart = findViewById(R.id.addToCart);
        quantity = findViewById(R.id.quantity);
        firebaseHelper = new FirebaseHelper();


        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.size_options,
                android.R.layout.simple_spinner_item
        );
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.color_options,
                android.R.layout.simple_spinner_item
        );
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorAdapter);

        Intent intent = getIntent();
        if(intent != null){
            ProductModel productModel = (ProductModel) intent.getSerializableExtra("product");

            if(productModel != null){
                Log.d(TAG,"Product received in Product Details");
                Log.d(TAG,productModel.toString());
                populateData(productModel);

                addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedSize = sizeSpinner.getSelectedItem().toString();
                        String selectedColor = colorSpinner.getSelectedItem().toString();
                        int quantityInt = Integer.parseInt(quantity.getText().toString());
                        CartItemModel cartItem = new CartItemModel(productModel,selectedSize,selectedColor,quantityInt);
                        firebaseHelper.addCartItem(cartItem);
                        startActivity(new Intent(ProductDetails.this,Bag.class));

                    }
                });
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