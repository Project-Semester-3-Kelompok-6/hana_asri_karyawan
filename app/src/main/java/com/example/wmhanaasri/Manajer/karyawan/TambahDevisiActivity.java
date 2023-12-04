package com.example.wmhanaasri.Manajer.karyawan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
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
import java.util.Map;

public class TambahDevisiActivity extends AppCompatActivity {
    private EditText etNamaSesi;
    private Button btnTambahkanSesi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manajer_activity_tambah_devisi);
        etNamaSesi = findViewById(R.id.et_namaSesiManajer);
        btnTambahkanSesi = findViewById(R.id.btnTambahkanDevisiManajer);

        btnTambahkanSesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    String nama = etNamaSesi.getText().toString().trim();
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.tambahDevisi,
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
                            Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("NamaDevisi", nama);
                            return params;
                        }
                    };

                    queue.add(stringRequest);
                }
            }
        });

    }
}