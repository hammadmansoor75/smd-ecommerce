package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    LinearLayout settings,shippingAddresses,paymentMethods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,Settings.class));
            }
        });

        shippingAddresses = findViewById(R.id.shippingAddresses);
        shippingAddresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,ShippingAddresses.class));
            }
        });

        paymentMethods = findViewById(R.id.paymentMethods);
        paymentMethods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,PaymentMethods.class));
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    // Handle Home item click
                    startActivity(new Intent(Profile.this, Home.class));
                    return true;
                } else if (itemId == R.id.action_shop) {
//                    startActivity(new Intent(HomeScreen.this, SearchScreen.class));
                    return true;
                } else if (itemId == R.id.action_profile) {
                    // Handle Profile item click

                    return true;
                } else if (itemId == R.id.action_chat) {
                    // Handle Profile item click
//                    startActivity(new Intent(HomeScreen.this, ChatScreen.class));
                    return true;
                }
                else if (itemId == R.id.action_bag) {
                    // Handle Profile item click
//                    startActivity(new Intent(HomeScreen.this,AddItemScreen.class));
                    return true;
                }
                return false;
            }
        });
    }
}