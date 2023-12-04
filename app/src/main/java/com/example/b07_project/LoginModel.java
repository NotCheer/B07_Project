package com.example.b07_project;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginModel {
    FirebaseDatabase db;
    public LoginModel(){
        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
    }

    public void queryDB(String name, String email, String password, LoginPresenter presenter){
        DatabaseReference userRef = db.getReference();
        DatabaseReference query = userRef.child("users").child(name);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(email.equals(snapshot.child("email").getValue(String.class))
                           && password.equals(snapshot.child("password")
                           .getValue(String.class)) && snapshot.exists()){
                    presenter.indicateJump(snapshot.child("identity").getValue(String.class));
                   }else {
                       presenter.Toast("Email or Password or Name Wrong");
                   }
               }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}

