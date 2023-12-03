package com.example.wmhanaasri.Karyawan.izin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PerizinanActivity extends AppCompatActivity {

    private static final int YOUR_CAMERA_REQUEST_CODE = 1001;
    private static final int YOUR_GALLERY_REQUEST_CODE = 1002;

    private AppCompatButton btnInputFile;
    EditText inputAlasan, inputTanggal;
    private String selectedFileName;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karyawan_activity_perizinan);

        inputAlasan = findViewById(R.id.et_Alasan);
        inputTanggal = findViewById(R.id.et_TanggalWaktu);

        btnInputFile = findViewById(R.id.btn_inputFile);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String karyawanID = preferences.getString("id", "1");
        Button btnAjukan = findViewById(R.id.btnAjukan);
        btnInputFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileOptions();
            }
        });

        // ...
        btnAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (bitmap != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.perizinan,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("success")) {
                                        Toast.makeText(getApplicationContext(), "Izin berhasil", Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Gagal izin", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("KaryawanID", karyawanID); // Ganti dengan ID Karyawan yang sesuai
                            params.put("Tanggal", inputTanggal.getText().toString()); // Ganti dengan tanggal yang sesuai
                            params.put("Alasan", inputAlasan.getText().toString()); // Ganti dengan status yang sesuai
                            params.put("image", base64Image); // Data gambar dalam bentuk base64
                            return params;
                        }
                    };

                    // Set header untuk menentukan tipe konten yang dikirimkan
                    stringRequest.setShouldCache(false);
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            0,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
                } else {
                    Toast.makeText(PerizinanActivity.this, "Masukkan File Pendukung", Toast.LENGTH_SHORT).show();
                }
            }
        });
// ...

        inputTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(); // Mengambil tanggal dan waktu terkini
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String currentDateAndTime = sdf.format(calendar.getTime());

                inputTanggal.setText(currentDateAndTime);
            }
        });


    }

    private void showFileOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PerizinanActivity.this);
        builder.setTitle("Pilih Sumber Gambar");

        builder.setPositiveButton("Kamera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, YOUR_CAMERA_REQUEST_CODE);
                }
            }
        });

        builder.setNegativeButton("Galeri", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, YOUR_GALLERY_REQUEST_CODE);
            }
        });

        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == YOUR_CAMERA_REQUEST_CODE) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                bitmap = imageBitmap; // Inisialisasi variabel bitmap dengan gambar yang diambil dari kamera
                selectedFileName = "ImageFromCamera.jpg";
            } else if (requestCode == YOUR_GALLERY_REQUEST_CODE) {
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    bitmap = imageBitmap; // Inisialisasi variabel bitmap dengan gambar yang diambil dari galeri
                    selectedFileName = "ImageFromGallery.jpg";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Update the button text with the selected file name
            if (selectedFileName != null) {
                btnInputFile.setText(selectedFileName);
            }
        }
    }
}
