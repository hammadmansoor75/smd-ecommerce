package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import adapters.BagAdapter;
import adapters.HomeAdapter;
import utils.CartItemModel;
import utils.ProductModel;

public class Bag extends AppCompatActivity {

    FirebaseHelper firebaseHelper;
    BagAdapter bagAdapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);

        firebaseHelper = new FirebaseHelper();
        rv = findViewById(R.id.recycler_view);

        firebaseHelper.getCartItems(new FirebaseHelper.OnCartItemsCompleteListener() {
            @Override
            public void onCartItemsLoadSuccess(List<CartItemModel> cartItems) {
                setUpRecyclerView(cartItems);
            }

            @Override
            public void onCartItemsFailure(String errorMessage) {
                Toast.makeText(Bag.this, "Error loading products: " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    startActivity(new Intent(Bag.this, Home.class));
                    return true;
                } else if (itemId == R.id.action_shop) {
                    startActivity(new Intent(Bag.this, Shop.class));
                    return true;
                } else if (itemId == R.id.action_profile) {
                    // Handle Profile item click
                    startActivity(new Intent(Bag.this, Profile.class));
                    return true;
                } else if (itemId == R.id.action_chat) {
                    // Handle Profile item click
                    startActivity(new Intent(Bag.this, Chat.class));
                    return true;
                }
                else if (itemId == R.id.action_bag) {
                    // Handle Profile item click
//                    startActivity(new Intent(Bag.this,Bag.class));
                    return true;
                }
                return false;
            }
        });
    }


    private void setUpRecyclerView(List<CartItemModel> cartItems) {
        bagAdapter = new BagAdapter(this, cartItems);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(bagAdapter);

        // Load images for each product
        for (CartItemModel cartItem : cartItems) {
            downloadAndDisplayImage(cartItem.getProduct());
        }
    }

    private void downloadAndDisplayImage(ProductModel product) {
        firebaseHelper.downloadImage(product.getImageUrl(), new FirebaseHelper.OnImageDownloadCompleteListener() {
            @Override
            public void onImageDownloadSuccess(byte[] imageData) {
                product.setImageData(imageData);
                bagAdapter.notifyDataSetChanged();
            }

            @Override
            public void onImageDownloadFailure(String errorMessage) {
                Toast.makeText(Bag.this, "Error downloading image: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}