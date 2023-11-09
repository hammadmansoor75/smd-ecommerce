package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    Button savePassBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        savePassBtn = findViewById(R.id.savePassBtn);
        savePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChangePassword.this,"Password Changed Successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ChangePassword.this,Profile.class));
            }
        });
    }
}