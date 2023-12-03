package com.example.b07_project;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginView extends AppCompatActivity{
    EditText editText_email,editText_password,editText_name;
    Button btn_login,btn_signup;
    LoginPresenter presenter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_password = findViewById(R.id.password);
        editText_email = findViewById(R.id.email);
        editText_name = findViewById(R.id.name);
        btn_signup = findViewById(R.id.btn_signup);
        btn_login = findViewById(R.id.btn_login);
        presenter = new LoginPresenter(new LoginModel(),this);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginView.this, Register.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_email.getText().toString();
                String password = editText_password.getText().toString();
                String name = editText_name.getText().toString();
                presenter.checkDB(name,email,password);
            }
        });
    }

    public void jumpAdmin(){
        Intent intent = new Intent(LoginView.this,
                AdminMain.class);
        startActivity(intent);
    }
    public void jumpStudent(){
        Intent intent = new Intent(LoginView.this,
                StudentsMain.class);
        startActivity(intent);
    }

    public void ToastMsg(String msg) {
        Toast.makeText(LoginView.this, msg, Toast.LENGTH_SHORT).show();
    }
}