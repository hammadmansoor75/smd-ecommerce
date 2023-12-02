package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import utils.ShippingAddressModel;

public class AddShippingAddress extends AppCompatActivity {

    ImageView navigateShippingAddresses;
    EditText username,address,city,state,zipCode,country;
    Button saveAddressBtn;
    ProgressBar progressBar;
    FirebaseHelper firebaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shipping_address);

        username = findViewById(R.id.username);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        zipCode = findViewById(R.id.zipCode);
        country = findViewById(R.id.country);
        progressBar = findViewById(R.id.progressBar);
        firebaseHelper = new FirebaseHelper();
        navigateShippingAddresses = findViewById(R.id.navigateShippingAddresses);
        navigateShippingAddresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddShippingAddress.this,ShippingAddresses.class));
            }
        });

        saveAddressBtn = findViewById(R.id.saveAddressBtn);
        saveAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ShippingAddressModel shippingAddress = new ShippingAddressModel(username.getText().toString(),address.getText().toString(),city.getText().toString(),state.getText().toString(),zipCode.getText().toString(),country.getText().toString());
                firebaseHelper.addShippingAddress(shippingAddress);
                Toast.makeText(AddShippingAddress.this,"Address Added Successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddShippingAddress.this,Profile.class));
            }
        });
    }
}