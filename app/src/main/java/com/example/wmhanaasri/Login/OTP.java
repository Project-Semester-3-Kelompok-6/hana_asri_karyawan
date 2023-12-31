package com.example.wmhanaasri.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.R;

import java.util.HashMap;
import java.util.Map;

public class OTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        String email = getIntent().getExtras().getString("email");
        EditText editTextNewPassword = findViewById(R.id.password);
        EditText editTextKonfirmasiPassword = findViewById(R.id.konfirPassword);
        EditText editTextOTP = findViewById(R.id.otp);
        Button button = findViewById(R.id.btnUbahPassword);
        ProgressBar progressBar = findViewById(R.id.progress);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Validasi OTP
                String otp = editTextOTP.getText().toString().trim();
                if (otp.isEmpty()) {
                    editTextOTP.setError("Masukkan kode OTP");
                    editTextOTP.requestFocus();
                    return;
                }

                // Validasi password
                String password = editTextNewPassword.getText().toString().trim();
                String konfirmasiPassword = editTextKonfirmasiPassword.getText().toString().trim();
                if (password.isEmpty()) {
                    editTextNewPassword.setError("Masukkan Password Baru");
                    editTextNewPassword.requestFocus();
                    return;
                }

                if (password.length() < 8) {
                    editTextNewPassword.setError("Password minimal 8 karakter");
                    editTextNewPassword.requestFocus();
                    return;
                }

                if (!password.equals(konfirmasiPassword)) {
                    editTextKonfirmasiPassword.setError("Password tidak cocok");
                    editTextKonfirmasiPassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.urlNewPassword,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                if (response.equals("success")) {
                                    Toast.makeText(getApplicationContext(), "New password Set", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        error.printStackTrace();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("email", email);
                        paramV.put("otp", editTextOTP.getText().toString());
                        paramV.put("new-password", editTextNewPassword.getText().toString());
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

    }
}