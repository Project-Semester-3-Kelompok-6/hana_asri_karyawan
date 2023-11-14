package com.example.wmhanaasri.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.Karyawan.KaryawanMainActivity;
import com.example.wmhanaasri.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Patterns;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailField = findViewById(R.id.etEmail);
        passwordField = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> {
            if (InputValidated()) {
                login();
            }
        });
    }

    private void login() {
        StringRequest request = new StringRequest(Request.Method.POST, DBConnect.urlLogin, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);

                int code = jsonObject.getInt("code");
                String status = jsonObject.getString("status");

                if (code == 200 && status.equals("Sukses")) {
                    JSONArray dataArray = jsonObject.getJSONArray("data");

                    if (dataArray.length() > 0) {
                        JSONObject res = dataArray.getJSONObject(0);

                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("id", res.getString("UserID"));
                        editor.putString("nama", res.getString("Nama"));
                        editor.putString("email", res.getString("email"));
                        editor.putString("password", res.getString("password"));
                        editor.putString("jabatan", res.getString("Role"));
                        editor.putString("status", res.getString("Status"));
                        editor.putString("devisi", res.getString("DevisiID"));
                        editor.apply();

                        startActivity(new Intent(LoginActivity.this, KaryawanMainActivity.class));
                        finish();

                        Toast.makeText(getApplicationContext(), "Login Sukses!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No data found in the response.", Toast.LENGTH_SHORT).show();
                    }
                } else if (code == 401) {
                    Toast.makeText(this, "Password atau Email Salah", Toast.LENGTH_SHORT).show();
                } else if (code == 404 && !status.equals("Sukses")) {
                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "JSON ERROR", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            error.printStackTrace();
            Toast.makeText(getApplicationContext(), "Network Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", emailField.getText().toString().trim());
                map.put("password", passwordField.getText().toString().trim());
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private boolean InputValidated() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        boolean isValidEmail = false;
        boolean isValidPassword = false;

        if (email.isEmpty()){
            emailField.setError("Isi Email");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError("Format email salah");
        }else{
            isValidEmail = true;
        }

        if (password.isEmpty()){
            passwordField.setError("Password Dibutuhkan");
        } else if (password.length()< 8) {
            passwordField.setError("Password Minimal 8");
        }else {
            isValidPassword = true;
        }

        btnLogin.setEnabled(isValidEmail && isValidPassword);

        return isValidEmail && isValidPassword;

    }
}
