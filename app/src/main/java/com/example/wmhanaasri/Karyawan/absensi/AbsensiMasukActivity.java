package com.example.wmhanaasri.Karyawan.absensi;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.wmhanaasri.R;
import com.karumi.dexter.BuildConfig;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.List;

public class AbsensiMasukActivity extends AppCompatActivity {

    private static final int REQ_CAMERA = 1001;
    private String imageFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karyawan_activity_absensi_masuk);

        // Mengganti layoutImage dengan id dari ImageView Anda
        ImageView layoutImage = findViewById(R.id.imageSelfie);
        layoutImage.setOnClickListener(v -> {
            Dexter.withContext(this)
                    .withPermissions(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                try {
                                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT);

                                    imageFilePath = createImageFile();
                                    if (imageFilePath != null) {
                                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(AbsensiMasukActivity.this,
                                                BuildConfig.APPLICATION_ID + ".provider",
                                                new File(imageFilePath)));
                                        startActivityForResult(cameraIntent, REQ_CAMERA);
                                    } else {
                                        Toast.makeText(AbsensiMasukActivity.this, "Gagal membuat file gambar", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException ex) {
                                    Toast.makeText(AbsensiMasukActivity.this, "Ups, gagal membuka kamera", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        });

    }

    private String createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(null);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        return image.getAbsolutePath();
    }
}

