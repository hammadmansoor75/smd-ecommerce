package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AddPaymentMethod extends AppCompatActivity {

    ImageView navigatePaymentMethods;
    Button saveAddressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);

        navigatePaymentMethods = findViewById(R.id.navigatePaymentMethods);
        navigatePaymentMethods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddPaymentMethod.this,PaymentMethods.class));
            }
        });

        saveAddressBtn = findViewById(R.id.saveAddressBtn);
        saveAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddPaymentMethod.this,"Payment Method Added Successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddPaymentMethod.this,Profile.class));
            }
        });
    }
}