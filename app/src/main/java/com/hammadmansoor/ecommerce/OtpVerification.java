package com.hammadmansoor.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OtpVerification extends AppCompatActivity {

    private EditText verificationCodeEditText;
    private TextView displayNumberTextView;

    // Variable to store the verification ID sent by Firebase
    private String verificationId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        // Initialize views
        verificationCodeEditText = findViewById(R.id.verificationCodeEditText);
        displayNumberTextView = findViewById(R.id.Display);

        // Set click listener for the "SEND" button
        Button verifyButton = findViewById(R.id.verifyButton);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSendButtonClicked(view);
            }
        });

        // Retrieve the verification ID passed from the previous activity
        verificationId = getIntent().getStringExtra("verificationId");


    }

    public void onSendButtonClicked(View view) {
        // Get the entered verification code
        String verificationCode = "000000";
        // Verify the code
        if (!verificationCode.isEmpty()) {
            // Pass the verification code to the signup class for final verification
            // For example, you can use an Intent to pass the verification code back to the signup class
            Intent intent = new Intent();
            intent.putExtra("verificationCode", verificationCode);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "Please enter the verification code", Toast.LENGTH_SHORT).show();
        }
    }

    // Other methods and code as needed

}
