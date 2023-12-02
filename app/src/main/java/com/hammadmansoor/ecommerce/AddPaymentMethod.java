package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import utils.PaymentMethodModel;

public class AddPaymentMethod extends AppCompatActivity {

    ImageView navigatePaymentMethods;
    EditText name,cardNumber,expireDate,cvv;
    Button saveAddressBtn;
    ProgressBar progressBar;

    FirebaseHelper firebaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);

        name = findViewById(R.id.name);
        cardNumber = findViewById(R.id.cardNumber);
        expireDate = findViewById(R.id.expireDate);
        cvv = findViewById(R.id.cvv);
        progressBar = findViewById(R.id.progressBar);
        firebaseHelper = new FirebaseHelper();

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
                progressBar.setVisibility(View.VISIBLE);
                PaymentMethodModel paymentMethod = new PaymentMethodModel(name.getText().toString(),cardNumber.getText().toString(),expireDate.getText().toString(),cvv.getText().toString());
                firebaseHelper.addPaymentMethod(paymentMethod);
                Toast.makeText(AddPaymentMethod.this,"Payment Method Added Successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddPaymentMethod.this,Profile.class));
            }
        });
    }
}