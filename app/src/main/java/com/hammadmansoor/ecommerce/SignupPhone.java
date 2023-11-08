package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignupPhone extends AppCompatActivity {

    TextView navigateSignupEmail,navigateLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_phone);

        navigateLogin = findViewById(R.id.navigateLogin);
        navigateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupPhone.this,LoginPhone.class));
            }
        });

        navigateSignupEmail = findViewById(R.id.navigateSignupEmail);
        navigateSignupEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupPhone.this,SignupEmail.class));
            }
        });
    }
}