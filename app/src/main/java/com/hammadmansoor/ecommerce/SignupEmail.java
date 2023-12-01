package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import utils.UserModel;

public class SignupEmail extends AppCompatActivity {
    TextView navigateLogin,navigateSignupPhone;
    EditText name,date,email,password;
    Button signUpBtn;
    FirebaseAuth mAuth;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_email);
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        firebaseStorage = new FirebaseStorage();

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


        signUpBtn = findViewById(R.id.signupBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                UserModel userModel = new UserModel(name.getText().toString(),email.getText().toString(),date.getText().toString(),mAuth.getUid());
                                firebaseStorage.createUser(userModel);
                                startActivity(new Intent(SignupEmail.this,Home.class));

                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(SignupEmail.this,"Something Went Wrong.Try Again!",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });


    }
}