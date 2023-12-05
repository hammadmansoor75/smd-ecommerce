package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import adapters.HomeAdapter;
import utils.ProductModel;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView homeRV;
    HomeAdapter homeAdapter;
    FirebaseHelper firebaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        homeRV = findViewById(R.id.recycler_view);
        firebaseHelper = new FirebaseHelper();

        firebaseHelper.getProducts(new FirebaseHelper.OnProductsCompleteListener() {
            @Override
            public void onProductsLoadSuccess(List<ProductModel> products) {
                setUpRecyclerView(products);
            }

            @Override
            public void onProductsLoadFailure(String errorMessage) {
                Toast.makeText(Home.this, "Error loading products: " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    // Handle Home item click
                    return true;
                } else if (itemId == R.id.action_shop) {
                    startActivity(new Intent(Home.this, Shop.class));
                    return true;
                } else if (itemId == R.id.action_profile) {
                    // Handle Profile item click
                    startActivity(new Intent(Home.this, Profile.class));
                    return true;
                } else if (itemId == R.id.action_chat) {
                    // Handle Profile item click
                    startActivity(new Intent(Home.this, Chat.class));
                    return true;
                }
                else if (itemId == R.id.action_bag) {
                    // Handle Profile item click
                    startActivity(new Intent(Home.this,Bag.class));
                    return true;
                }
                return false;
            }
        });
    }


    private void setUpRecyclerView(List<ProductModel> products) {
        homeAdapter = new HomeAdapter(this, products);

        homeRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        homeRV.setAdapter(homeAdapter);

        // Load images for each product
        for (ProductModel product : products) {
            downloadAndDisplayImage(product);
        }
    }

    private void downloadAndDisplayImage(ProductModel product) {
        firebaseHelper.downloadImage(product.getImageUrl(), new FirebaseHelper.OnImageDownloadCompleteListener() {
            @Override
            public void onImageDownloadSuccess(byte[] imageData) {
                product.setImageData(imageData);
                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onImageDownloadFailure(String errorMessage) {
                Toast.makeText(Home.this, "Error downloading image: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}