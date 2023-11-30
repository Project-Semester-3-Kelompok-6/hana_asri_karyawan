package com.example.wmhanaasri.Karyawan.izin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.wmhanaasri.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PerizinanActivity extends AppCompatActivity {

    private static final int YOUR_CAMERA_REQUEST_CODE = 1001;
    private static final int YOUR_GALLERY_REQUEST_CODE = 1002;

    private AppCompatButton btnInputFile;
    EditText inputAlasan,inputTanggal;
    private String selectedFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karyawan_activity_perizinan);

        inputAlasan = findViewById(R.id.et_Alasan);
        inputTanggal = findViewById(R.id.et_TanggalWaktu);

        btnInputFile = findViewById(R.id.btn_inputFile);
        btnInputFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileOptions();
            }
        });

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
                // Handle the image from the camera
                // For now, let's assume a dummy file name for the camera
                selectedFileName = "ImageFromCamera.jpg";
            } else if (requestCode == YOUR_GALLERY_REQUEST_CODE) {
                Uri selectedImageUri = data.getData();
                // Handle the image from the gallery
                // Retrieve the file name from the Uri or use a dummy name
                selectedFileName = "ImageFromGallery.jpg";
            }

            // Update the button text with the selected file name
            if (selectedFileName != null) {
                btnInputFile.setText(selectedFileName);
            }
        }
    }
}
