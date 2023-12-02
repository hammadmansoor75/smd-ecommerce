package com.hammadmansoor.ecommerce;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.PaymentMethodsAdapter;
import adapters.ShippingAddressAdapter;
import utils.PaymentMethodModel;
import utils.ShippingAddressModel;

public class PaymentMethods extends AppCompatActivity {
    ImageView navigateProfile;
    TextView addPaymentMethod;
    RecyclerView paymentMethodsRV;
    PaymentMethodsAdapter paymentMethodsAdapter;
    List<PaymentMethodModel> paymentMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);
        paymentMethodsRV = findViewById(R.id.paymentMethodsRV);
        paymentMethods = new ArrayList<PaymentMethodModel>();
        paymentMethodsAdapter = new PaymentMethodsAdapter(paymentMethods);
        paymentMethodsRV.setLayoutManager(new LinearLayoutManager(this));
        paymentMethodsRV.setAdapter(paymentMethodsAdapter);


        navigateProfile = findViewById(R.id.navigateProfile);
        navigateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentMethods.this,Profile.class));
            }
        });

        addPaymentMethod = findViewById(R.id.addPaymentMethod);
        addPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentMethods.this,AddPaymentMethod.class));
            }
        });

    }

    public void getUserPaymentMethods() {
        FirebaseHelper.getPaymentMethods(new FirebaseHelper.OnPaymentMethodsCompleteListener() {
            @Override
            public void onPaymentMethodsLoaded(List<PaymentMethodModel> paymentMethods) {
                Log.d(TAG, "Number of payment methods received: " + paymentMethods.size());
                paymentMethods.clear();
                // Add the new addresses to the list
                paymentMethods.addAll(paymentMethods);
                // Notify the adapter about the data change
                paymentMethodsAdapter.notifyDataSetChanged();
                Log.v(TAG, "Payment Methods RECEIVED IN Payment Methods CLASS");
            }

            @Override
            public void onPaymentMethodsError(Exception e) {
                Log.e(TAG, "Error loading payment methods: " + e.getMessage());
            }
        });
    }

}