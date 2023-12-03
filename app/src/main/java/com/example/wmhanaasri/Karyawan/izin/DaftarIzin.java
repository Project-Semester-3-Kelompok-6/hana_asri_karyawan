package com.example.wmhanaasri.Karyawan.izin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.Karyawan.izin.adapter.RVListIzinAdapter;
import com.example.wmhanaasri.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DaftarIzin extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private RecyclerView recyclerView;
    private RVListIzinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_izin);



        sharedPreferences = getSharedPreferences("listizin", MODE_PRIVATE);
        recyclerView = findViewById(R.id.rv_listIzin);
        fetchData();
    }
    public void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String karyawanID = preferences.getString("id", "1");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.listIzin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray dataArray = new JSONArray(response);

                            if (dataArray.length() > 0) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject obj = dataArray.getJSONObject(i);
                                    editor.putString("IzinID" + i, obj.getString("IzinID"));
                                    editor.putString("KaryawanID" + i, obj.getString("KaryawanID"));
                                    editor.putString("Tanggal" + i, obj.getString("Tanggal"));
                                    editor.putString("Alasan" + i, obj.getString("Alasan"));
                                    editor.putString("BuktiDokumen" + i, obj.getString("BuktiDokumen"));
                                }
                                editor.apply();
                                Toast.makeText(DaftarIzin.this, "Diperbarui", Toast.LENGTH_SHORT).show();

                                int dataSize = 0;
                                while (sharedPreferences.contains("Tanggal" + dataSize)) {
                                    dataSize++;
                                }

                                setupRecyclerView(dataSize);
                            } else {
                                Toast.makeText(DaftarIzin.this, "Tidak ada data yang diterima", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DaftarIzin.this, "Terjadi kesalahan dalam pengolahan data JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(DaftarIzin.this, "Gagal melakukan request data dari server", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> paramV = new HashMap<>();
                paramV.put("KaryawanID", karyawanID); // Ganti dengan ID Karyawan yang sesuai
                return paramV;
            }
        };
        queue.add(stringRequest);
    }

    private void setupRecyclerView(int dataSize) {
        adapter = new RVListIzinAdapter(this, dataSize); // Menggunakan this sebagai context
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Menggunakan this sebagai context
        recyclerView.setAdapter(adapter);
    }
}