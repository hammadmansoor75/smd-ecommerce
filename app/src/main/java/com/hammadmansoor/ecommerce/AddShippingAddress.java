package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AddShippingAddress extends AppCompatActivity {

    ImageView navigateShippingAddresses;
    Button saveAddressBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shipping_address);

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
                Toast.makeText(AddShippingAddress.this,"Address Added Successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddShippingAddress.this,Profile.class));
            }
        });
    }
}