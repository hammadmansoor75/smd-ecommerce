package com.hammadmansoor.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    Button savePassBtn;
    EditText newPass,repeatPass;
    FirebaseUser currentUser;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        newPass = findViewById(R.id.newPass);
        repeatPass = findViewById(R.id.repeatPass);
        progressBar = findViewById(R.id.progressBar);

        savePassBtn = findViewById(R.id.savePassBtn);
        savePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                updatePassword();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }


    public void updatePassword(){
        if(currentUser != null){
            if(newPass.getText().toString().equals(repeatPass.getText().toString())){
                currentUser.updatePassword(newPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(ChangePassword.this,"Password Changed Successfully",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ChangePassword.this,Profile.class));
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(ChangePassword.this,"Something went wrong.Try Again",Toast.LENGTH_LONG).show();
                            }
                        });

            }else{
                Toast.makeText(ChangePassword.this,"Passwords don't match. Try Again",Toast.LENGTH_LONG).show();
            }
        }
    }
}