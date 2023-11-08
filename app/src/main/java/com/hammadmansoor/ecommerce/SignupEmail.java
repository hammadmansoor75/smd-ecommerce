package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignupEmail extends AppCompatActivity {
    TextView navigateLogin,navigateSignupPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_email);

        navigateLogin = findViewById(R.id.navigateLogin);
        navigateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupEmail.this,LoginEmail.class));
            }
        });

        navigateSignupPhone = findViewById(R.id.navigateSignupPhone);
        navigateSignupPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupEmail.this,SignupPhone.class));
            }
        });


    }
}