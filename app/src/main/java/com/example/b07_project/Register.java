package com.example.b07_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    TextInputEditText editText_email,editText_password,editText_confirmed_password,
            editText_name,editText_identity;
    Button btn_signup;
    ImageView imageView;
    FirebaseDatabase db;
    DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        userRef = db.getReference();
        editText_password = findViewById(R.id.password);
        editText_confirmed_password = findViewById(R.id.confirmed_password);
        editText_email = findViewById(R.id.email);
        editText_name = findViewById(R.id.name);
        editText_identity = findViewById(R.id.identity);
        btn_signup = findViewById(R.id.btn_register);
        imageView = findViewById(R.id.imageView);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, confirmed_password,name,identity;
                email = String.valueOf(editText_email.getText());
                password = editText_password.getText().toString();
                name = editText_name.getText().toString();
                identity = editText_identity.getText().toString();
                confirmed_password = editText_confirmed_password.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"Enter Email",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this,"Enter Password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmed_password)){
                    Toast.makeText(Register.this,"Enter Confirmed Password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!(password.equals(confirmed_password))){
                    Toast.makeText(Register.this,"Confirmed Password Wrong",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!(identity.equals("Student") || identity.equals("Admin"))){
                    Toast.makeText(Register.this,"Identity Wrong",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    userRef.child("users").child(name).addListenerForSingleValueEvent
                            (new ValueEventListener() {
                        @Override
                        public void onDataChange( DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                Toast.makeText(Register.this,"Name Exists",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }else{
                                User user = new User(email,password,identity,name);
                                writeNewUser(user);
                                Intent intent = new Intent(Register.this,
                                        LoginView.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled( DatabaseError error) {
                        }
                    });
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LoginView.class);
                startActivity(intent);
            }
        });
    }

    public void writeNewUser(User user) {
        userRef.child("users").child(user.name).setValue(user);
    }
}