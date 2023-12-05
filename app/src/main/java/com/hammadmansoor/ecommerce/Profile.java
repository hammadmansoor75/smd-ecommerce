package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import helper.UserHelper;
import utils.UserModel;

public class Profile extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;
    LinearLayout settings,shippingAddresses,paymentMethods,myOrders;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        retrieveUserData(mAuth.getUid());

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

        myOrders = findViewById(R.id.myOrders);
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, MyOrders.class));
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(ContextCompat.getColorStateList(this,R.drawable.item_selector));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    // Handle Home item click
                    startActivity(new Intent(Profile.this, Home.class));
                    return true;
                } else if (itemId == R.id.action_shop) {
                    startActivity(new Intent(Profile.this, Shop.class));
                    return true;
                } else if (itemId == R.id.action_profile) {
                    // Handle Profile item click

                    return true;
                } else if (itemId == R.id.action_chat) {
                    // Handle Profile item click
                    startActivity(new Intent(Profile.this, Chat.class));
                    return true;
                }
                else if (itemId == R.id.action_bag) {
                    // Handle Profile item click
                    startActivity(new Intent(Profile.this,Bag.class));
                    return true;
                }
                return false;
            }
        });
    }


    private void retrieveUserData(String userId) {

        UserHelper.getUserData(userId, new UserHelper.OnUserDataListener() {
            @Override
            public void onUserDataLoaded(UserModel document) {
                TextView profileName = findViewById(R.id.profileName);
                TextView profileEmail = findViewById(R.id.profileEmail);

                profileName.setText(document.getName());
                profileEmail.setText(document.getEmail());
            }

            @Override
            public void onUserDataNotFound() {
                // Handle case when user data is not found

            }

            @Override
            public void onUserDataError(Exception e) {
                // Handle error loading user data

            }
        });
    }
}