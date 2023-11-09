package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShippingAddresses extends AppCompatActivity {
    ImageView navigateProfile;
    TextView addShippingAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_addresses);

        navigateProfile = findViewById(R.id.navigateProfile);
        navigateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShippingAddresses.this,Profile.class));
            }
        });

        addShippingAddress = findViewById(R.id.addShippingAddress);
        addShippingAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShippingAddresses.this,AddShippingAddress.class));
            }
        });
    }
}