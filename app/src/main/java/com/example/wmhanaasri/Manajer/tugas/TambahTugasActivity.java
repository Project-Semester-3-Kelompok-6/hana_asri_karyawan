package com.example.wmhanaasri.Manajer.tugas;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TambahTugasActivity extends AppCompatActivity {

    private EditText etTanggalTugasManajer, editTextJudul, editTextDeskripsi;
    private DatePickerDialog datePickerDialog;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manajer_activity_tambah_tugas);
        AutoCompleteTextView dropDivisi = findViewById(R.id.dropdown_divisiTugasManajer);
        AutoCompleteTextView dropKaryawan = findViewById(R.id.dropdown_karyawan);

        //set dropDevisi
        SharedPreferences sharedPreferences = getSharedPreferences("getDevisi", Context.MODE_PRIVATE);
        Set<String> devisiSet = sharedPreferences.getStringSet("devisi_set", new HashSet<>());
        String[] devisiArray = devisiSet.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, devisiArray);
        dropDivisi.setAdapter(adapter);

        //set dropKaryawan
        SharedPreferences Preferences = getSharedPreferences("getKaryawan", Context.MODE_PRIVATE);
        Set<String> KaryawanSet = Preferences.getStringSet("karyawan_set", new HashSet<>());
        String[] karyawanArray = KaryawanSet.toArray(new String[0]);
        ArrayAdapter<String> adapterKaryawan = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, karyawanArray);
        dropKaryawan.setAdapter(adapterKaryawan);

        editTextJudul = findViewById(R.id.et_judulTugasManajer);
        editTextDeskripsi = findViewById(R.id.et_deskripsiTugasManajer);
        etTanggalTugasManajer = findViewById(R.id.et_tangggalTugasManajer);
        Button btnTambahTugas = findViewById(R.id.btnTambahTugasManajer);

        // Mendapatkan tanggal sekarang
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        etTanggalTugasManajer.setFocusable(false); // Menghilangkan fokus dari EditText
        etTanggalTugasManajer.setClickable(true); // Membuatnya masih dapat diklik

        etTanggalTugasManajer.setOnClickListener(v -> {
            // Munculkan DatePickerDialog saat EditText diklik
            datePickerDialog = new DatePickerDialog(TambahTugasActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        // Set Tanggal yang dipilih ke dalam EditText dengan format YYYY-MM-DD
                        String formattedMonth = String.format("%02d", monthOfYear + 1);
                        String formattedDayOfMonth = String.format("%02d", dayOfMonth);
                        String selectedDate = year + "-" + formattedMonth + "-" + formattedDayOfMonth;
                        etTanggalTugasManajer.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });

        btnTambahTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judul = editTextJudul.getText().toString().trim();
                String deskripsi = editTextDeskripsi.getText().toString().trim();
                String tanggal = etTanggalTugasManajer.getText().toString().trim();
                String devisi = dropDivisi.getText().toString().trim();
                String karyawan = dropKaryawan.getText().toString();
                // Mengambil hanya satu karakter pertama dari teks

                String status = "Task";

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.UrlTambahTugas,
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
                        params.put("Judul", judul);
                        params.put("Deskripsi", deskripsi);
                        params.put("DevisiID", "1");
                        params.put("KaryawanID", "1");
                        params.put("Tanggal", tanggal);
                        params.put("Status", status);
                        return params;
                    }
                };

                queue.add(stringRequest);
            }
        });



    }
}