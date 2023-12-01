package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordEmail extends AppCompatActivity {
    Button forgetPassBtn;
    EditText email;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_email);
        mAuth = FirebaseAuth.getInstance();
        forgetPassBtn = findViewById(R.id.forgetPassBtn);
        email = findViewById(R.id.email);

        forgetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ForgotPasswordEmail.this,"Password Reset Email Sent",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ForgotPasswordEmail.this,LoginEmail.class));
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(ForgotPasswordEmail.this,"Something Went Wrong.Try Again",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}