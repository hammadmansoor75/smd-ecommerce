package com.hammadmansoor.ecommerce;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import utils.ShippingAddressModel;

public class EditAddress extends AppCompatActivity {
    EditText username,addressActivity,city,state,zipCode,country;
    ImageView navigateShippingAddresses;
    ProgressBar progressBar;
    Button saveAddressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        username = findViewById(R.id.username);
        addressActivity = findViewById(R.id.address);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        zipCode = findViewById(R.id.zipCode);
        country = findViewById(R.id.country);
        progressBar = findViewById(R.id.progressBar);
        navigateShippingAddresses = findViewById(R.id.navigateShippingAddresses);
        navigateShippingAddresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditAddress.this,ShippingAddresses.class));
            }
        });
        saveAddressBtn = findViewById(R.id.saveAddressBtn);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("addressModel")) {
            ShippingAddressModel address = (ShippingAddressModel) getIntent().getSerializableExtra("address");

            // Set initial values in EditText fields
            if (address != null) {
                username.setText(address.getUsername());
                addressActivity.setText(address.getAddress());
                city.setText(address.getCity());
                state.setText(address.getState());
                zipCode.setText(address.getZipCode());
                country.setText(address.getCountry());

                saveAddressBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle the save button click
                        saveEditedAddress(address);
                    }
            }    );}

        } else {
            // Handle the case where address details are not provided
            Toast.makeText(this, "Invalid address details", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no valid address details are available
        }
    }

    public void saveEditedAddress(ShippingAddressModel address){
        Log.println(Log.ASSERT,TAG,address.getAddressId());
    }
}