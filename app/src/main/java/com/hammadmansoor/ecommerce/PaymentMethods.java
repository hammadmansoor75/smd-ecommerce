package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PaymentMethods extends AppCompatActivity {
    ImageView navigateProfile;
    TextView addPaymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);


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
}