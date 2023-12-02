package com.hammadmansoor.ecommerce;

import android.graphics.Bitmap;

public class FashionItem {
    private Bitmap imageBitmap; // Updated to use Bitmap instead of imageResource
    private String title;
    private String description;
    private String price;

    public FashionItem(Bitmap imageBitmap, String title, String description, String price) {
        this.imageBitmap = imageBitmap;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    // Getter for imageBitmap
    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    // Setter for imageBitmap
    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }


}
