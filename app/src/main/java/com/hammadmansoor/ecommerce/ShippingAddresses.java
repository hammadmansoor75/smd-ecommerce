package com.hammadmansoor.ecommerce;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.util.Assert;

import java.util.ArrayList;
import java.util.List;

import adapters.ShippingAddressAdapter;
import utils.ShippingAddressModel;

public class ShippingAddresses extends AppCompatActivity {
    ImageView navigateProfile;
    TextView addShippingAddress;
    RecyclerView shippingAddressRV;
    ShippingAddressAdapter shippingAddressAdapter;
    List<ShippingAddressModel> shippingAddresses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_addresses);

        shippingAddressRV = findViewById(R.id.shippingAddressRV);
        shippingAddresses = new ArrayList<ShippingAddressModel>();
        shippingAddressAdapter = new ShippingAddressAdapter(shippingAddresses);
        shippingAddressRV.setLayoutManager(new LinearLayoutManager(this));
        shippingAddressRV.setAdapter(shippingAddressAdapter);
        getUserShippingAddresses();
        shippingAddressAdapter.setOnItemClickListener(new ShippingAddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ShippingAddressModel address) {
                // Handle the "Edit" button click
                openEditAddressActivity(address);
            }
        });

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


    public void getUserShippingAddresses() {
        FirebaseHelper.getShippingAddresses(new FirebaseHelper.OnShippingAddressesCompleteListener() {
            @Override
            public void onShippingAddressesLoaded(List<ShippingAddressModel> addresses) {
                // Clear the existing list before adding new addresses
                Log.d(TAG, "Number of addresses received: " + addresses.size());
                shippingAddresses.clear();
                // Add the new addresses to the list
                shippingAddresses.addAll(addresses);
                // Notify the adapter about the data change
                shippingAddressAdapter.notifyDataSetChanged();
                Log.v(TAG, "SHIPPING ADDRESSES RECEIVED IN SHIPPING ADDRESSES CLASS");
            }

            @Override
            public void onShippingAddressesError(Exception e) {
                // Handle error
                Log.e(TAG, "Error loading shipping addresses: " + e.getMessage());
            }
        });
    }

    private void openEditAddressActivity(ShippingAddressModel address) {
        // Implement navigation to the edit address activity
        // Pass the address details to the edit screen if needed
        Intent intent = new Intent(ShippingAddresses.this, EditAddress.class);
        Log.e(TAG,address.getAddressId());
        intent.putExtra("addressModel", address);
        startActivity(intent);
    }

}