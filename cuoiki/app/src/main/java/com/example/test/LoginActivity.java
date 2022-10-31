package com.example.test;

import static com.example.test.MainActivity.http;
import static com.example.test.MainActivity.islogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView gotoRegister;
    EditText txtEmail,txtPass;
    public static ArrayList<User> Users = new ArrayList<User>();
    public static int idUser;
    private String URL = http+"androidwebservice/login.php";
    public static  User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtEmail = findViewById(R.id.inputEmail);
        txtPass = findViewById(R.id.inputPassword);

        gotoRegister = (TextView) findViewById(R.id.gotoRegister);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        btnLogin =  findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               login();
            }
        });


    }
    public void login() {
        String mUser = txtEmail.getText().toString().trim();
        String mPass  = txtPass.getText().toString().trim();
        islogin = true;
        if(!mUser.equals("") && !mPass.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonObject  = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        JSONArray jsonArray = jsonObject.getJSONArray("login");
                        //Toast.makeText(LoginActivity.this,jsonArray.toString() ,Toast.LENGTH_SHORT).show();
                        if(success.equals("1")){
                            Toast.makeText(LoginActivity.this,"Đăng nhập thành công!" ,Toast.LENGTH_SHORT).show();
                            for(int i = 0 ; i < jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);


                                user.setIdUser(object.getInt("idUser"));
                                user.setIdUser(object.getInt("idUser"));
                                user.setUsername(object.getString("Username").trim());
                                user.setPass(object.getString("Pass").trim());
                                user.setPhone(object.getString("phone").trim());
                                user.setDob(object.getString("dob").trim());
                                user.setAddress(object.getString("address").trim());
                                user.setFullname(object.getString("fullname").trim());
                                Users.add(user);


                                //Toast.makeText(LoginActivity.this,"Tên: " +  user.getUsername(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                    }catch(JSONException e){
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this,"Error: " + e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", mUser);
                    data.put("password", mPass);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }


}