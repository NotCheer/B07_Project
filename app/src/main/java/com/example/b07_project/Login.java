package com.example.b07_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class Login extends AppCompatActivity {

    TextInputEditText editText_email,editText_password,editText_name;
    Button btn_login,btn_signup;
    FirebaseDatabase db;

    DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = FirebaseDatabase.getInstance("https://b07project-f0761-default-rtdb." +
                "firebaseio.com/");
        editText_password = findViewById(R.id.password);
        editText_email = findViewById(R.id.email);
        editText_name = findViewById(R.id.name);
        btn_signup = findViewById(R.id.btn_signup);
        btn_login = findViewById(R.id.btn_login);
        userRef = db.getReference();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_email.getText().toString();
                String password = editText_password.getText().toString();
                String name = editText_name.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(Login.this,"Enter Name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this,"Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this,"Enter Password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                userRef.child("users").child(name).addListenerForSingleValueEvent
                        (new ValueEventListener() {
                            @Override
                            public void onDataChange( DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    String emails = snapshot.child("email").
                                            getValue(String.class);
                                    String passwords = snapshot.child("password").
                                            getValue(String.class);
                                    if(!(emails.equals(email) || passwords.equals(password))){
                                        Toast.makeText(Login.this,
                                                "Email or Password Wrong",
                                                Toast.LENGTH_SHORT).show();
                                        return;
                                    }else{
                                        Intent intent = new Intent(Login.this,
                                                MainActivity.class);
                                        startActivity(intent);
                                    }

                                }else{
                                    Toast.makeText(Login.this,"Name Does Not Exists",
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }

                            @Override
                            public void onCancelled( DatabaseError error) {
                            }
                        });
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}