package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignupPhone extends AppCompatActivity {

    TextView navigateSignupEmail, navigateLogin;
    FirebaseAuth firebaseAuth;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_phone);

        firebaseAuth = FirebaseAuth.getInstance();

        navigateLogin = findViewById(R.id.navigateLogin);
        navigateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupPhone.this, LoginPhone.class));
            }
        });

        navigateSignupEmail = findViewById(R.id.signupPhone);
        navigateSignupEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the phone authentication process
                startPhoneAuthentication();
            }
        });
    }

    private void startPhoneAuthentication() {
        // You can add code here to validate the phone number if needed

        // Start the Firebase Phone Authentication process
        String phoneNumber = "+923165583118"; // Replace with the user's phone number
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        // Automatically handle verification if the code is received without user input
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(SignupPhone.this, "Verification failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        // Save the verification ID and start the OtpVerification activity
                        verificationId = s;
                        startOtpVerificationActivity();
                    }
                }
        );
    }

    private void startOtpVerificationActivity() {
        Intent intent = new Intent(SignupPhone.this, OtpVerification.class);
        intent.putExtra("verificationId", verificationId);
        startActivity(intent);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(SignupPhone.this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Verification completed successfully
                            Toast.makeText(SignupPhone.this, "Verification completed successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // Verification failed
                            Toast.makeText(SignupPhone.this, "Verification failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
