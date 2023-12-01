package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoginPhone extends AppCompatActivity {
    TextView navigatePass,navigateLoginEmail;
    FirebaseAuth mAuth;
    EditText phone,password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);
        mAuth = FirebaseAuth.getInstance();
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);

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

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPhone.this,Home.class));
            }
        });
    }
}