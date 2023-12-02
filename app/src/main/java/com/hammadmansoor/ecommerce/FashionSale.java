package com.hammadmansoor.ecommerce;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import helper.ImageUploadHelper;

public class FashionSale extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FashionAdapter fashionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion_sale);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        fashionAdapter = new FashionAdapter(new ArrayList<>());

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(fashionAdapter);

        // Initialize DatabaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("fashionItems");

        // Fetch data from Firebase and update the adapter
        fetchFashionItems();
    }

    private void fetchFashionItems() {
        // Attach a listener to read the data at our fashionItems reference
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FashionItem> fashionItems = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Replace "imageUrl", "itemName", "description", and "price" with your actual field names
                    String imageUrl = snapshot.child("imageUrl").getValue(String.class);
                    String itemName = snapshot.child("itemName").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String price = snapshot.child("price").getValue(String.class);

                    // Create FashionItem object with a placeholder image resource
                    FashionItem fashionItem = new FashionItem(null, itemName, description, price);
                    fashionItems.add(fashionItem);

                    // Now, load the actual image from Firebase Storage using the imageUrl
                    loadImageFromFirebase(imageUrl, fashionItem);
                }

                // Update the adapter with the fetched data
                fashionAdapter.setFashionItems(fashionItems);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    private void loadImageFromFirebase(String imageUrl, FashionItem fashionItem) {
        // Use the ImageUploadHelper to download the image from Firebase Storage
        ImageUploadHelper imageUploadHelper = new ImageUploadHelper(this);
        imageUploadHelper.downloadImage(imageUrl)
                .addOnSuccessListener(bitmap -> {
                    // Set the downloaded image to the FashionItem
                    fashionItem.setImageBitmap(bitmap);
                    // Notify the adapter that data has changed
                    fashionAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // Handle the image download failure
                    Toast.makeText(FashionSale.this, "Image download failed", Toast.LENGTH_LONG).show();
                });
    }

    }

