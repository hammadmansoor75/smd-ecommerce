package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginPhone extends AppCompatActivity {
    TextView navigatePass,navigateLoginEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);

        navigatePass = findViewById(R.id.navigatePass);
        navigatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPhone.this,ForgotPasswordPhone.class));
            }
        });

        navigateLoginEmail = findViewById(R.id.navigateLoginEmail);
        navigateLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPhone.this,LoginEmail.class));
            }
        });
    }
}