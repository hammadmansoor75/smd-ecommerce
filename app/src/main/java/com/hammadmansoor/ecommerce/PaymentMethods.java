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
import utils.PaymentMethodModel;

public class PaymentMethods extends AppCompatActivity {
    ImageView navigateProfile;
    TextView addPaymentMethod;
    RecyclerView paymentMethodsRV;
    PaymentMethodsAdapter paymentMethodsAdapter;
    List<PaymentMethodModel> paymentMethodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);
        paymentMethodsRV = findViewById(R.id.paymentMethodsRV);
        paymentMethodsList = new ArrayList<PaymentMethodModel>();
        paymentMethodsAdapter = new PaymentMethodsAdapter(paymentMethodsList);
        paymentMethodsRV.setLayoutManager(new LinearLayoutManager(this));
        paymentMethodsRV.setAdapter(paymentMethodsAdapter);
        getUserPaymentMethods();


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
                paymentMethodsList.clear();
                // Add the new addresses to the list
                paymentMethodsList.addAll(paymentMethods);
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