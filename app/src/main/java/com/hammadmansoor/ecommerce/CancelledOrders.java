package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CancelledOrders extends AppCompatActivity {

    TextView delivered,processing,cancelled;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelled_orders);

        delivered = findViewById(R.id.delivered);
        processing = findViewById(R.id.processing);
        cancelled = findViewById(R.id.cancelled);

        delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CancelledOrders.this, MyOrders.class));
            }
        });

        processing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CancelledOrders.this,ProcessingOrders.class));
            }
        });

        cancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    // Handle Home item click
                    startActivity(new Intent(CancelledOrders.this, Home.class));
                    return true;
                } else if (itemId == R.id.action_shop) {
//                    startActivity(new Intent(HomeScreen.this, SearchScreen.class));
                    return true;
                } else if (itemId == R.id.action_profile) {
                    // Handle Profile item click
                    startActivity(new Intent(CancelledOrders.this, Profile.class));

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