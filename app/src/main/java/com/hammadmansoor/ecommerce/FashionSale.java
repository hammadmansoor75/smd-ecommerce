package com.hammadmansoor.ecommerce;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FashionSale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion_sale);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // Create a list of FashionItems (replace this with your data)
        List<FashionItem> fashionItemList = generateFashionItems();

        // Create and set the adapter
        FashionAdapter fashionAdapter = new FashionAdapter(fashionItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(fashionAdapter);
    }

    // Replace this method with your actual data source
    private List<FashionItem> generateFashionItems() {
        List<FashionItem> fashionItems = new ArrayList<>();

        // Add FashionItems to the list based on your data
        // Replace the placeholders with your actual data
        fashionItems.add(new FashionItem(R.drawable.newpic, "Sweatshirt", "Full Day Dress", "10$"));
        fashionItems.add(new FashionItem(R.drawable.newpic, "Sweatshirt", "Full Day Dress", "10$"));

        return fashionItems;
    }
}
