package utils;

public class CartItemModel {
    ProductModel product;
    String size;
    String color;
    int quantity;
    double totalPrice;
    String cartItemId;

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public CartItemModel(ProductModel product, String size, String color, int quantity) {
        this.product = product;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
//        double productPrice = Double.parseDouble(product.getPrice().replaceAll("[^\\d.]",""));
//        this.totalPrice =productPrice * quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
