package com.example.wmhanaasri.Karyawan.home;

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
import com.example.wmhanaasri.Karyawan.home.adapter.RVSesiKaryawan;
import com.example.wmhanaasri.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JadwalActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferencesSesiKaryawan;
    private RVSesiKaryawan adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karyawan_activity_jadwal);
        sharedPreferencesSesiKaryawan = getSharedPreferences("sesikaryawan", Context.MODE_PRIVATE);
        fetchData();

    }
    public void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DBConnect.listSesi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray dataArray = new JSONArray(response);

                            if (dataArray.length() > 0) {
                                SharedPreferences.Editor editor = sharedPreferencesSesiKaryawan.edit();

                                // Ambil data dari setiap objek JSON dalam array dan simpan dalam SharedPreferences
                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject obj = dataArray.getJSONObject(i);
                                    editor.putString("idSesi" + i, obj.getString("idSesi"));
                                    editor.putString("namaSesi" + i, obj.getString("namaSesi"));
                                    editor.putString("deskripsi" + i, obj.getString("deskripsi"));
                                    editor.putString("awalSesi" + i, obj.getString("awalSesi"));
                                    editor.putString("akhirSesi" + i, obj.getString("akhirSesi"));
                                }
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Diperbarui", Toast.LENGTH_SHORT).show();

                                // Hitung jumlah data yang disimpan dalam SharedPreferences
                                int dataSize = 0;
                                while (sharedPreferencesSesiKaryawan.contains("namaSesi" + dataSize)) {
                                    dataSize++;
                                }

                                setupRecyclerView(dataSize); // Setup RecyclerView dengan jumlah data yang ada
                            } else {
                                Toast.makeText(getApplicationContext(), "Tidak ada data yang diterima", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan dalam pengolahan data JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Gagal melakukan request data dari server", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
    private void setupRecyclerView(int dataSize) {
        adapter = new RVSesiKaryawan(getApplicationContext(), dataSize);
        RecyclerView recyclerView = findViewById(R.id.rv_sesiKaryawan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }
}