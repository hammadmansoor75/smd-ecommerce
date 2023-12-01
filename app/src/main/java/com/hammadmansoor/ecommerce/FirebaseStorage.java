package com.hammadmansoor.ecommerce;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import utils.UserModel;

public class FirebaseStorage {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public void createUser(UserModel userModel){
        firestore.collection("users").add(userModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                System.out.println("New User Created");
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                System.out.println("Something went wrong creating user");
            }
        });
    }
}
