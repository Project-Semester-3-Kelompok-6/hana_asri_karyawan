package com.example.wmhanaasri.Manajer.karyawan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TambahKaryawanActivity extends AppCompatActivity {
    private EditText etNama, etEmail, etPassword;
    private Button btnTambahkan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manajer_activity_tambah_karyawan);

        etNama = findViewById(R.id.etKaryawan);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnTambahkan = findViewById(R.id.btnTambahkan);
        AutoCompleteTextView dropRole = findViewById(R.id.dropRole);
        AutoCompleteTextView dropStatus = findViewById(R.id.dropStatus);
        AutoCompleteTextView dropDivisi = findViewById(R.id.dropDivisi);

        //set dropRole
        String[] roleOptions = {"Manajer", "Karyawan"};

        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, roleOptions);

        dropRole.setAdapter(roleAdapter);


        //set dropStatus
        String[] statusOptions = {"Karyawan tetap", "Karyawan tidak tetap"};

        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, statusOptions);

        dropStatus.setAdapter(statusAdapter);

        //set dropDevisi
        SharedPreferences sharedPreferences = getSharedPreferences("getDevisi", Context.MODE_PRIVATE);
        Set<String> devisiSet = sharedPreferences.getStringSet("devisi_set", new HashSet<>());
        String[] devisiArray = devisiSet.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, devisiArray);
        dropDivisi.setAdapter(adapter);


        btnTambahkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String role = dropRole.getText().toString();
                String status = dropStatus.getText().toString();
                String devisiID = "1"; // Ganti dengan nilai yang sesuai dengan id devisi yang dipilih

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.UrlTambahKaryawan,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    String message = jsonResponse.getString("message");

                                    if (success) {
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                        onBackPressed();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("nama", nama);
                        params.put("email", email);
                        params.put("password", password);
                        params.put("role", role);
                        params.put("status", status);
                        params.put("devisiID", devisiID);
                        return params;
                    }
                };

                queue.add(stringRequest);
            }
        });
    }

//    private void tambahkanKaryawan(String nama, String email, String password, String role, String status, int devisiID) {
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.UrlTambahKaryawan,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if (response.equals("success")){
//                            Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(getApplicationContext(), KaryawanFragment.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        else{
//                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("nama", nama);
//                params.put("email", email);
//                params.put("password", password);
//                params.put("role", role);
//                params.put("status", status);
//                params.put("devisiID", "1 - Marketing Sosmed" );
//                return params;
//            }
//        };
//
//        requestQueue.add(stringRequest);
//    }

    }
