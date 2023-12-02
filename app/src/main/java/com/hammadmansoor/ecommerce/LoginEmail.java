package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginEmail extends AppCompatActivity {

    TextView navigatePass,navigateLoginPhone,navigateAdminLogin;
    Button loginBtn;
    EditText email,password;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        navigatePass = findViewById(R.id.navigatePass);
        navigatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginEmail.this,ForgotPasswordEmail.class));
            }
        });

        navigateAdminLogin = findViewById(R.id.navigateAdminLogin);
        navigateAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginEmail.this,AdminLogin.class));
            }
        });

        navigateLoginPhone = findViewById(R.id.navigateLoginPhone);
        navigateLoginPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginEmail.this,LoginPhone.class));
            }
        });

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        startActivity(new Intent(LoginEmail.this,Home.class));
                                    }
                                }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginEmail.this, "Something went wrong. Try Again!", Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });


    }
}