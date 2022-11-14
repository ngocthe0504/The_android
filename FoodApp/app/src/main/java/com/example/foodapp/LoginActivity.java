package com.example.foodapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_REGISTER = 1;
    EditText edtUserName;
    EditText edtPassword;
    Button btnLogin;
    Button btnRegister;
    private String userName = "admin";
    private String password = "admin";
    String email = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ndUser = edtUserName.getText().toString();
                String ndPass = edtPassword.getText().toString();
                if (ndUser.length() == 0|| ndPass.length() == 0) {
                    Toast.makeText(LoginActivity.this,"Tên đăng nhập hoặc mật khẩu không được để trống!", Toast.LENGTH_LONG).show();
                } else if(ndUser.equals(userName) && ndPass.equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, ListFoodActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,"Tên đăng nhập hoặc mật khẩu sai!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_REGISTER:
                userName = data.getStringExtra("userName");
                password = data.getStringExtra("password");
                email = data.getStringExtra("email");

                break;
        }
    }
}