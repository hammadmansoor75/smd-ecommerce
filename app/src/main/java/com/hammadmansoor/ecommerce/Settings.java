package com.hammadmansoor.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import helper.UserHelper;
import utils.UserModel;

public class Settings extends AppCompatActivity {
    TextView changePassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mAuth = FirebaseAuth.getInstance();
        retrieveUserData(mAuth.getUid());

        changePassword = findViewById(R.id.changePassword);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,ChangePassword.class));
            }
        });
    }

    private void retrieveUserData(String userId) {

        UserHelper.getUserData(userId, new UserHelper.OnUserDataListener() {
            @Override
            public void onUserDataLoaded(UserModel document) {
                TextView name = findViewById(R.id.name);
                TextView dob = findViewById(R.id.dob);

                name.setText(document.getName());
                dob.setText(document.getDate());
            }

            @Override
            public void onUserDataNotFound() {
                // Handle case when user data is not found

            }

            @Override
            public void onUserDataError(Exception e) {
                // Handle error loading user data

            }
        });
    }
}