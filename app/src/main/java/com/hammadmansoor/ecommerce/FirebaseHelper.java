package com.hammadmansoor.ecommerce;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import utils.PaymentMethodModel;
import utils.ShippingAddressModel;
import utils.UserModel;

public class FirebaseHelper {
    FirebaseFirestore firestore;
    FirebaseUser mAuth;

    FirebaseHelper(){
        this.firestore = FirebaseFirestore.getInstance();
        this.mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }



    public void createUser(UserModel userModel){
        firestore.collection("users").document(userModel.getUid()).set(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    public void addShippingAddress(ShippingAddressModel shippingAddress) {

        // Add shipping address document to "shipping_addresses" collection
        firestore.collection("shipping_addresses")
                .document(mAuth.getUid())
                .collection("addresses")
                .add(shippingAddress).
                addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                    }
                });
    }

    public void addPaymentMethod(PaymentMethodModel paymentMethod) {

        // Add shipping address document to "shipping_addresses" collection
        firestore.collection("payment_methods")
                .document(mAuth.getUid())
                .collection("cards")
                .add(paymentMethod).
                addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                    }
                });
    }



    public interface OnShippingAddressesCompleteListener {
        void onShippingAddressesLoaded(List<ShippingAddressModel> addresses);
        void onShippingAddressesError(Exception e);
    }

    public static void getShippingAddresses(OnShippingAddressesCompleteListener listener) {
        // Get the current user's UID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Reference to the shipping addresses collection for the current user
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("shipping_addresses")
                .document(userId)
                .collection("addresses")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<ShippingAddressModel> addresses = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            // Convert each document to a ShippingAddress object
                            String username = document.getString("username");
                            String address = document.getString("address");
                            String city = document.getString("city");
                            String state = document.getString("state");
                            String zipCode = document.getString("zipCode");
                            String country = document.getString("country");
                            String addressId = document.getId();
                            ShippingAddressModel addressModel = new ShippingAddressModel(username,address,city,state,zipCode,country,addressId);
                            addresses.add(addressModel);
                        }
                        // Notify the listener with the list of addresses
                        listener.onShippingAddressesLoaded(addresses);
                        Log.v(TAG, "Shipping Addresses GOT ");
                    } else {
                        // Handle error
                        Log.e(TAG, "Error getting shipping addresses", task.getException());
                        listener.onShippingAddressesError(task.getException());
                    }
                });
    }

    public interface OnPaymentMethodsCompleteListener {
        void onPaymentMethodsLoaded(List<PaymentMethodModel> paymentMethods);
        void onPaymentMethodsError(Exception e);
    }


    public static void getPaymentMethods(OnPaymentMethodsCompleteListener listener) {
        // Get the current user's UID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Reference to the shipping addresses collection for the current user
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("payment_methods")
                .document(userId)
                .collection("cards")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<PaymentMethodModel> paymentMethods = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            // Convert each document to a ShippingAddress object
                            String name = document.getString("name");
                            String expireDate = document.getString("expireDate");
                            String cardNumber = document.getString("cardNumber");
                            String cvv = document.getString("cvv");
                            PaymentMethodModel paymentMethodModel = new PaymentMethodModel(name,expireDate,cardNumber,cvv);
                            paymentMethods.add(paymentMethodModel);
                        }
                        // Notify the listener with the list of addresses
                        listener.onPaymentMethodsLoaded(paymentMethods);
                        Log.v(TAG, "GOT PAYMENT METHODS");
                    } else {
                        // Handle error
                        Log.e(TAG, "Error getting payment methods", task.getException());
                        listener.onPaymentMethodsError(task.getException());
                    }
                });
    }


}
