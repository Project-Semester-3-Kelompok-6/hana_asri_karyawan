package com.example.wmhanaasri.Manajer.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
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
import java.util.Locale;
import java.util.Map;

public class TambahSesiActivity extends AppCompatActivity {
    private EditText editTextNamaSesi,editTextDeskripsi,editTextAwalSesi,editTextAkhirSesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_sesi);
        editTextNamaSesi = findViewById(R.id.textJudul);
        editTextDeskripsi = findViewById(R.id.deskripsi);
        editTextAwalSesi = findViewById(R.id.awalSesi);
        editTextAkhirSesi = findViewById(R.id.akhirSesi);
        editTextAwalSesi.setOnClickListener(v -> showTimePicker(editTextAwalSesi));
        editTextAkhirSesi.setOnClickListener(v -> showTimePicker(editTextAkhirSesi));

        Button btnTambahSesi = findViewById(R.id.btnTambahkanSesi);

        btnTambahSesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = editTextNamaSesi.getText().toString().trim();
                String deskripsi = editTextDeskripsi.getText().toString().trim();
                String awal = editTextAwalSesi.getText().toString().trim();
                String akhir = editTextAkhirSesi.getText().toString().trim();
                // Mengambil hanya satu karakter pertama dari teks
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.tambahSesi,
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
                        params.put("namaSesi", nama);
                        params.put("deskripsi", deskripsi);
                        params.put("awalSesi", awal);
                        params.put("akhirSesi", akhir);
                        return params;
                    }
                };

                queue.add(stringRequest);
            }
        });
    }

    private void showTimePicker(EditText editText) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    // Format waktu yang dipilih ke dalam string 24 jam
                    String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                    editText.setText(selectedTime);
                },
                // Waktu awal pada picker
                0, 0, true // Atur waktu awal ke pukul 00:00 dengan format 24 jam
        );

        timePickerDialog.show();
    }
}
