package com.example.wmhanaasri.Karyawan.tugas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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
import java.util.HashMap;
import java.util.Map;

public class UpdateStatusTugas extends AppCompatActivity {
    private AppCompatButton btnInputFile;
    private static final int YOUR_CAMERA_REQUEST_CODE = 1001;
    private static final int YOUR_GALLERY_REQUEST_CODE = 1002;
    Bitmap bitmap;
    String selectedFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karyawan_activity_update_status_tugas);

        btnInputFile = findViewById(R.id.btn_inputFile);
        Button btnSelesai = findViewById(R.id.btnTugasKaryawanSelesai);

        btnInputFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileOptions();
            }
        });

        Intent intent = getIntent();
        String judul = intent.getStringExtra("judul");

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                String base64Image = "";

                if (bitmap != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
                }

                String finalBase64Image = base64Image;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.DoneTugas,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("success")) {
                                    Toast.makeText(getApplicationContext(), "Tugas selesai", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Gagal menyelesaikan tugas", Toast.LENGTH_LONG).show();
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
                        params.put("Judul", judul);
                        params.put("image", finalBase64Image);
                        return params;
                    }
                };

                stringRequest.setShouldCache(false);
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        0,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                if (bitmap != null || base64Image.isEmpty()) {
                    Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
                } else {
                    Toast.makeText(UpdateStatusTugas.this, "Masukkan File Pendukung", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showFileOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStatusTugas.this);
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
                bitmap = imageBitmap;
                selectedFileName = "ImageFromCamera.jpg";
            } else if (requestCode == YOUR_GALLERY_REQUEST_CODE) {
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    bitmap = imageBitmap;
                    selectedFileName = "ImageFromGallery.jpg";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (selectedFileName != null) {
                btnInputFile.setText(selectedFileName);
            }
        }
    }
}
