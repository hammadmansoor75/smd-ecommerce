package com.hammadmansoor.ecommerce;

public class FashionItem {
    private int imageResource;
    private String title;
    private String description;
    private String price;

    public FashionItem(int imageResource, String title, String description, String price) {
        this.imageResource = imageResource;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public int getImageResource() {
        return imageResource;
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
