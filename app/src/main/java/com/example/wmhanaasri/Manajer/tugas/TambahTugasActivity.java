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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
import java.util.HashSet;
import java.util.Set;

public class TambahTugasActivity extends AppCompatActivity {

    private EditText etTanggalTugasManajer, editTextJudul, editTextDeskripsi;
    private DatePickerDialog datePickerDialog;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manajer_activity_tambah_tugas);
        fetchData();
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
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
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
                        // Set Tanggal yang dipilih ke dalam EditText
                        etTanggalTugasManajer.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }, year, month, day);
            datePickerDialog.show();
        });

        btnTambahTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });



    }
    public void fetchData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this); // Menggunakan "this" karena berada dalam Activity

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                DBConnect.getKaryawan, // Ubah URL ini sesuai dengan lokasi API PHP Anda
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Set<String> karyawanSet = new HashSet<>();

                        try {
                            // Ambil data dari respons JSON
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject karyawan = response.getJSONObject(i);
                                String userID = karyawan.getString("UserID");
                                String nama = karyawan.getString("Nama");

                                // Gabungkan UserID dan Nama dalam satu string
                                String karyawanData = userID + "-" + nama;

                                // Tambahkan ke dalam Set
                                karyawanSet.add(karyawanData);
                            }

                            // Simpan Set ke dalam SharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences("getKaryawan", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putStringSet("karyawan_set", karyawanSet);
                            editor.apply();

                            // Tampilkan dalam logcat untuk memeriksa hasil
                            Log.d("KaryawanSet", karyawanSet.toString());

                            // Setelah menyimpan data, pindah ke TambahTugasActivity

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle kesalahan pada request
                        error.printStackTrace();
                    }
                }
        );

        // Tambahkan request ke dalam antrian
        requestQueue.add(jsonArrayRequest);
    }
}