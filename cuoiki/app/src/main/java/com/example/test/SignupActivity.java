package com.example.test;

import static com.example.test.MainActivity.http;
import static com.example.test.MainActivity.islogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    Button btnSignup;
    EditText  editUserName,editPass1,editPass2,editPhone,editEmail,editAddress;
    private String URL = http+"androidwebservice/signup.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Anhxa();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editUserName.getText().toString().trim().equals("") ||  editPass1.getText().toString().trim().equals("")
                ||editPass2.getText().toString().trim().equals("") || editPhone.getText().toString().trim().equals("")
                ||editEmail.getText().toString().trim().equals("")||editAddress.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    SaveUser();
                }


            }
        });
    }

    private void SaveUser() {
        String username = editUserName.getText().toString().trim();
        String pass1 = editPass1.getText().toString().trim();
        String pass2 = editPass2.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String fullname = editEmail.getText().toString().trim();
        String address = editAddress.getText().toString().trim();

        if(pass1.equals(pass2)){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(SignupActivity.this,"Đăng kí thành công!" ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignupActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("Username", username);
                    data.put("Pass", pass1);
                    data.put("phone", phone);
                    data.put("address", address);
                    data.put("fullname", fullname);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
        }
    }

    private void Anhxa() {
        editUserName =findViewById(R.id.inputUserName);
        editPass1  =findViewById(R.id.inputPass);
        editPass2 = findViewById(R.id.inputPass2);
        editPhone  = findViewById(R.id.inputPhone);
        editEmail = findViewById(R.id.inputEmail);
        editAddress = findViewById(R.id.inputAddress);
        btnSignup = findViewById(R.id.btnSignup);
    }
}