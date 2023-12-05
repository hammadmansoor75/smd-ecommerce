package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import adapters.HomeAdapter;
import utils.ProductModel;

public class Shop extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView rv;
    HomeAdapter adapter;
    FirebaseHelper firebaseHelper;
    List<ProductModel> originalProducts;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        rv = findViewById(R.id.recycler_view);
        firebaseHelper = new FirebaseHelper();

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

        firebaseHelper.getProducts(new FirebaseHelper.OnProductsCompleteListener() {
            @Override
            public void onProductsLoadSuccess(List<ProductModel> products) {
                originalProducts = new ArrayList<>(products);
                setUpRecyclerView(products);
            }

            @Override
            public void onProductsLoadFailure(String errorMessage) {
                Toast.makeText(Shop.this, "Error loading products: " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    startActivity(new Intent(Shop.this, Home.class));
                    return true;
                } else if (itemId == R.id.action_shop) {
//                    startActivity(new Intent(Shop.this, Shop.class));
                    return true;
                } else if (itemId == R.id.action_profile) {
                    // Handle Profile item click
                    startActivity(new Intent(Shop.this, Profile.class));
                    return true;
                } else if (itemId == R.id.action_chat) {
                    // Handle Profile item click
                    startActivity(new Intent(Shop.this, Chat.class));
                    return true;
                }
                else if (itemId == R.id.action_bag) {
                    // Handle Profile item click
                    startActivity(new Intent(Shop.this,Bag.class));
                    return true;
                }
                return false;
            }
        });
    }


    private void setUpRecyclerView(List<ProductModel> products) {
        adapter = new HomeAdapter(this, products);

        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(adapter);

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
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onImageDownloadFailure(String errorMessage) {
                Toast.makeText(Shop.this, "Error downloading image: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        List<ProductModel> filteredProducts = filterProducts(originalProducts, query);

        // Update the RecyclerView with the filtered data
        adapter.setProductList(filteredProducts);
        adapter.notifyDataSetChanged();

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<ProductModel> filteredProducts = filterProducts(originalProducts, newText);

        // Update the RecyclerView with the filtered data
        adapter.setProductList(filteredProducts);
        adapter.notifyDataSetChanged();

        return true;
    }


    private List<ProductModel> filterProducts(List<ProductModel> products, String query) {
        // Implement your filtering logic here
        // For simplicity, this example checks if the product name contains the query (case-insensitive)
        List<ProductModel> filteredList = new ArrayList<>();

        for (ProductModel product : products) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }

        return filteredList;
    }
}