package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import helper.ImageUploadHelper;
import helper.ProductHelper;
import utils.ProductModel;

public class AdminAddProduct extends AppCompatActivity {

    ImageUploadHelper imageUploadHelper;
    EditText name,description,price,category;
    Button add;
    ImageView pickImages;
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        category = findViewById(R.id.category);
        imageUploadHelper = new ImageUploadHelper(this);
        pickImages = findViewById(R.id.pickImages);
        add = findViewById(R.id.Add);

        pickImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedImageUri != null){
                    addProduct();
                    startActivity(new Intent(AdminAddProduct.this,AdminHome.class));
                }else{
                    Toast.makeText(AdminAddProduct.this, "Please pick an image first", Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Handle the selected image URI
            selectedImageUri = data.getData();
            // Optionally, you can display the selected image to the user
             pickImages.setImageURI(selectedImageUri);
        }
    }

    private void addProduct() {
        // Use the selectedImageUri and other product details to upload to Firestore
        String productName = name.getText().toString();
        String productDescription = description.getText().toString();
        String productPrice = price.getText().toString();
        String productCategory = category.getText().toString();

        // Use the ImageUploadHelper to upload the image to Firebase Storage
        ImageUploadHelper imageUploadHelper = new ImageUploadHelper(this);
        imageUploadHelper.uploadProductImage(selectedImageUri)
                .addOnSuccessListener(downloadUrl -> {
                    // Image uploaded successfully, now upload product details to Firestore
                    ProductModel productModel = new ProductModel(productName,productDescription,productCategory,downloadUrl,productPrice);
                    ProductHelper.uploadProduct(productModel);
                    Toast.makeText(AdminAddProduct.this, "Product added successfully", Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> {
                    // Handle the image upload failure
                    Toast.makeText(AdminAddProduct.this, "Image upload failed", Toast.LENGTH_LONG).show();
                });
    }
}